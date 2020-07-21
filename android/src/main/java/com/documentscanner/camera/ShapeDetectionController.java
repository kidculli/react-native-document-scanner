package com.documentscanner.camera;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.documentscanner.R;
import com.documentscanner.helpers.ImageProcessor;
import com.documentscanner.helpers.CustomOpenCVLoader;
import com.documentscanner.helpers.ImageProcessorMessage;
import com.documentscanner.helpers.ScannedDocument;
import com.facebook.react.bridge.WritableMap;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;


public class ShapeDetectionController extends CameraDeviceController {
    private HandlerThread mImageThread;
    private ImageProcessor mImageProcessor;
    private int numberOfRectangles = 15;
    private boolean imageProcessorBusy = true;
    private int filterId = 1;

    public ShapeDetectionController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeDetectionController(Context context, Integer numCam, Activity activity, FrameLayout frameLayout) {
        super(context, numCam, activity, frameLayout);
    }


    public void setImageProcessorBusy(boolean isBusy) {
      this.imageProcessorBusy = isBusy;
    }

    public int getFilterId() {
      return this.filterId;
    }

    /**
     Sets the currently active filter
     */
    public void setFilterId(int filterId) {
      this.filterId = filterId;
    }

    //================================================================================
    // Image Detection
    //================================================================================

    /**
     Runs each frame the image is being pushed to the preview layer
     */
    @Override
    public void processOutput(Mat image) {
      detectRectangleFromImageLater(image);
    }

    /**
     Looks for a rectangle in the given image async
     */
    private void detectRectangleFromImageLater(Mat image) {
      if (!imageProcessorBusy) {
          setImageProcessorBusy(true);
          Message msg = mImageProcessor.obtainMessage();
          msg.obj = new ImageProcessorMessage("previewFrame", image);
          mImageProcessor.sendMessageDelayed(msg, 100);
      }
    }

    /**
     Called after a frame is processed and a rectangle was found
     */
    public void rectangleWasDetected(WritableMap detection) {}

    //================================================================================
    // Capture Image
    //================================================================================

    /**
    After an image is captured, this fuction is called and handles cropping the image
    */
    @Override
    public void handleCapturedImage(Mat capturedImage) {
      setImageProcessorBusy(true);
      Message msg = mImageProcessor.obtainMessage();
      msg.obj = new ImageProcessorMessage("pictureTaken", capturedImage);
      mImageProcessor.sendMessageAtFrontOfQueue(msg);
    }

    /**
     After an image is captured and cropped, this method is called
     */
    public void onProcessedCapturedImage(ScannedDocument scannedDocument) {

    }
}