package com.rodrigodelcanto.mobile.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;

import com.couchbase.lite.Attachment;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.Status;
import com.couchbase.lite.UnsavedRevision;
import com.rodrigodelcanto.mobile.commons.util.ImageUtil;
import com.rodrigodelcanto.people.Application;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by delkant on 5/28/17.
 *
 * Persistence Actions
 */

public class P {
    private static final String TAG = "Persistence";
    private static final int THUMBNAIL_SIZE = 150;



    public static Document save(Map<String, Object> properties) {
        return save(properties, true);
    }

    public static Document save(Map<String, Object> properties, Boolean ignoreConflicts) {
        Document document = Application.getPersistence().getDatabase().getDocument((String) properties.get("id"));
        try {
            document.putProperties(properties);
        } catch (CouchbaseLiteException e) {
            if (e.getCBLStatus().getCode() == Status.CONFLICT && !ignoreConflicts) {
                Log.e(TAG, "On Create " + properties.get("schema") + " :" + e.getMessage());
            } else if (e.getCBLStatus().getCode() == Status.CONFLICT) {
                document = update(document.getId(), properties);
            } else {
                Log.e(TAG, "On Create: " + properties.toString(), e);
            }
        }

        return document;
    }


    public static Document update(Document doc, String key, Object value) {
        Map<String, Object> updatedProperties = new HashMap<String, Object>();
        updatedProperties.put(key, value);
        return update(doc.getId(), updatedProperties);
    }

    public static Document update(String id, final Map<String, Object> properties) {
        Application app = Application.getApp();
        Document document = app.getPersistence().getDatabase().getExistingDocument(id);
        try {
            document.update(new Document.DocumentUpdater() {
                @Override
                public boolean update(UnsavedRevision newRevision) {
                    Map updatedProperties = newRevision.getUserProperties();
                    updatedProperties.putAll(properties);
                    newRevision.setUserProperties(updatedProperties);
                    return true;
                }
            });
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "On Conflict resolution"
                    + ", type:" + properties.get("schema")
                    + ", error :" + e.getMessage());
        }
        return document;
    }

    public static void delete(Document document) {
        try {
            document.delete();
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "On Delete", e);
        }
    }

    public static void attachImage(Document doc, Bitmap image) {
        if (doc == null || image == null) return;
        Map img = new HashMap<String, Object>();
        img.put("schema", "demo:image");
        Document imageDoc = save(img);

        UnsavedRevision revision = imageDoc.createRevision();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 80, out);
        InputStream in = new ByteArrayInputStream(out.toByteArray());
        revision.setAttachment("image", "image/jpg", in);

        try {
            revision.save();
            update(doc, "image", imageDoc.getId());
        } catch (CouchbaseLiteException e) {
            com.couchbase.lite.util.Log.e(Application.TAG, "Cannot attach image", e);
        }
    }

    private Bitmap getImageThumbnail(Document doc) {
        List<Attachment> attachments = doc.getCurrentRevision().getAttachments();
        if (attachments.size() == 0)
            return null;

        Bitmap bitmap = null;
        InputStream is = null;
        final int size = THUMBNAIL_SIZE;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            is = attachments.get(0).getContent();
            BitmapFactory.decodeStream(is, null, options);
            options.inSampleSize = ImageUtil.calculateInSampleSize(options, size, size);
            is.close();

            options.inJustDecodeBounds = false;
            is = doc.getCurrentRevision().getAttachments().get(0).getContent();
            bitmap = BitmapFactory.decodeStream(is, null, options);
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, size, size);
        } catch (Exception e) {
            com.couchbase.lite.util.Log.e(Application.TAG, "Cannot decode the attached image", e);
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
        }
        return bitmap;
    }

    public static Bitmap getBitmap(String id, String imageFieldKey) {
        Application app = Application.getApp();
        Document holder = app.getPersistence().getDatabase().getDocument(id);
        String imageId = (String) holder.getProperty("image");
        if (imageId == null)
            return null;
        Document document = app.getPersistence().getDatabase().getDocument(imageId);
        Attachment attachment = (document.getCurrentRevision() != null) ? document.getCurrentRevision().getAttachment(imageFieldKey) : null;
        if (attachment == null)
            return null;

        Bitmap image = null;
        try {
            image = createScaledBitmapFromStream(attachment.getContent(), 100, 100);
        } catch (Exception e) {
            Log.e(TAG, "Cannot display the attached image", e);
        }

        return image;
    }

    public static Bitmap getBitmap(String id, String imageFieldKey, int width, int height) {
        Bitmap b = getBitmap(id, imageFieldKey);

        return (b != null) ? b.createScaledBitmap(b, width, height, false) : b;
    }

    public static Bitmap getBitmap(String id, String imageFieldKey, int scaleSize) {
        Bitmap bitmap = getBitmap(id, imageFieldKey);

        return scale(bitmap, scaleSize);
    }

    public static Bitmap scale(Bitmap bitmap, int scaleSize) {
        Bitmap resizedBitmap = null;
        if (bitmap != null) {
            int originalWidth = bitmap.getWidth();
            int originalHeight = bitmap.getHeight();
            int newWidth = -1;
            int newHeight = -1;
            float multFactor = -1.0F;
            if (originalHeight > originalWidth) {
                newHeight = scaleSize;
                multFactor = (float) originalWidth / (float) originalHeight;
                newWidth = (int) (newHeight * multFactor);
            } else if (originalWidth > originalHeight) {
                newWidth = scaleSize;
                multFactor = (float) originalHeight / (float) originalWidth;
                newHeight = (int) (newWidth * multFactor);
            } else if (originalHeight == originalWidth) {
                newHeight = scaleSize;
                newWidth = scaleSize;
            }
            resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
        }
        return resizedBitmap;
    }

    /**
     * Read the image from the stream and save a bitmap scaled to the desired
     * size.  Resulting bitmap will be at least as large as the
     * desired minimum specified dimensions and will keep the image proportions
     * correct during scaling.
     */
    protected static Bitmap createScaledBitmapFromStream(InputStream s, int minimumDesiredBitmapWith, int minimumDesiredBitmapHeight) {

        final BufferedInputStream is = new BufferedInputStream(s, 32 * 1024);
        try {
            final BitmapFactory.Options decodeBitmapOptions = new BitmapFactory.Options();
            // For further memory savings, you may want to consider using this option
            // decodeBitmapOptions.inPreferredConfig = Config.RGB_565; // Uses 2-bytes instead of default 4 per pixel

            if (minimumDesiredBitmapWith > 0 && minimumDesiredBitmapHeight > 0) {
                final BitmapFactory.Options decodeBoundsOptions = new BitmapFactory.Options();
                decodeBoundsOptions.inJustDecodeBounds = true;
                is.mark(32 * 1024); // 32k is probably overkill, but 8k is insufficient for some jpgs
                BitmapFactory.decodeStream(is, null, decodeBoundsOptions);
                is.reset();

                final int originalWidth = decodeBoundsOptions.outWidth;
                final int originalHeight = decodeBoundsOptions.outHeight;

                // inSampleSize prefers multiples of 2, but we prefer to prioritize memory savings
                decodeBitmapOptions.inSampleSize = Math.max(1, Math.min(originalWidth / minimumDesiredBitmapWith, originalHeight / minimumDesiredBitmapHeight));

            }

            return BitmapFactory.decodeStream(is, null, decodeBitmapOptions);

        } catch (IOException e) {
            throw new RuntimeException(e); // this shouldn't happen
        } finally {
            try {
                is.close();
            } catch (IOException ignored) {
            }
        }

    }
}
