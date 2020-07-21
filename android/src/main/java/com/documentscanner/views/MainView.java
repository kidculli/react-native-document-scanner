package com.documentscanner.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.documentscanner.R;

public class MainView extends FrameLayout {
    private ScannerView view;

    public static MainView instance = null;

    public static MainView getInstance() {
        return instance;
    }

    public static void createInstance(Context context, Activity activity) {
        instance = new MainView(context, activity);
    }

    public MainView(Context context, Activity activity) {
        super(context);

        LayoutInflater lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FrameLayout frameLayout = (FrameLayout) lf.inflate(R.layout.activity_scanner, null);

        view = new ScannerView(context, -1, activity, frameLayout);
        view.setParent(this);
        addViewInLayout(view, 0, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addViewInLayout(frameLayout, 1, view.getLayoutParams());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(l, t, r, b);
        }
    }

    public void setDetectionCountBeforeCapture(int numberOfRectangles) {
        view.setDetectionCountBeforeCapture(numberOfRectangles);
    }

    public void setDurationBetweenCaptures(double durationBetweenCaptures) {
        view.setDurationBetweenCaptures(durationBetweenCaptures);
    }

    public void setEnableTorch(boolean enable) {
        view.setEnableTorch(enable);
    }

    public void setOnScannerListener(ScannerView.OnScannerListener listener) {
        view.setOnScannerListener(listener);
    }

    public void setOnProcessingListener(ScannerView.OnProcessingListener listener) {
        view.setOnProcessingListener(listener);
    }

    public void setOverlayColor(String rgbaColor) {
        view.setOverlayColor(rgbaColor);
    }

    public void setSaveOnDevice(Boolean saveOnDevice) {
        view.setSaveOnDevice(saveOnDevice);
    }

    public void setBrightness(double brightness) {
        view.setBrightness(brightness);
    }

    public void setContrast(double contrast) {
        view.setContrast(contrast);
    }

    public void setManualOnly(boolean manualOnly) {
        view.setManualOnly(manualOnly);
    }

    public void setCapturedQuality(double quality) {
        view.setCapturedQuality(quality);
    }

    public void setFilterId(int filterId) {
        view.setFilterId(filterId);
    }

    public void startCamera() {
        view.startCamera();
    }

    public void stopCamera() {
        view.stopCamera();
    }

    public void cleanupCamera() {
        view.cleanupCamera();
    }

    public void focusCamera() {
      view.focusCamera();
    }

    public void capture() {
        view.capture();
    }

    public void setShowBorder(Boolean enable) {
        view.setShowBorder(enable);
    }

    public void setFaceDetection(Boolean enable) {
        view.setFaceDetection(enable);
    }

    // Use only when face detection is on
    public void setPhotoHeight(double height) {
        view.setPhotoHeight(height);
    }

    // Use only when face detection is on
    public void setPhotoWidth(double width) {
        view.setPhotoWidth(width);
    }

    // Double page scan (like passport)
    public void setDoublePageScan(Boolean enable) {
        view.setDoublePageScan(enable);
    }

    public void deviceWasSetup(WritableMap config) {
      final ReactContext context = (ReactContext) getContext();
      context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onDeviceSetup", config);
    }

    public void torchWasChanged(boolean torchEnabled) {
      WritableMap map = new WritableNativeMap();
      map.putBoolean("enabled", torchEnabled);
      final ReactContext context = (ReactContext) getContext();
      context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onTorchChanged", map);
    }

    public void rectangleWasDetected(WritableMap detection) {
      final ReactContext context = (ReactContext) getContext();
      context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onRectangleDetected", detection);
    }

    public void pictureWasTaken(WritableMap pictureDetails) {
      final ReactContext context = (ReactContext) getContext();
      context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onPictureTaken", pictureDetails);
    }

    public void pictureWasProcessed(WritableMap pictureDetails) {
      final ReactContext context = (ReactContext) getContext();
      context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onPictureProcessed", pictureDetails);
    }

    public void pictureDidFailToProcess(WritableMap errorDetails) {
      final ReactContext context = (ReactContext) getContext();
      context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onErrorProcessingImage", errorDetails);
    }
}
