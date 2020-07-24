package com.documentscanner;

import com.documentscanner.views.MainView;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.WritableMap;

public class DocumentScannerModule extends ReactContextBaseJavaModule{
    public DocumentScannerModule(ReactApplicationContext reactContext){
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNScannerManager";
    }
    
    @ReactMethod
    public void start(){
        MainView view = MainView.getInstance();
        view.startCamera();
    }

    @ReactMethod
    public void stop(){
        MainView view = MainView.getInstance();
        view.stopCamera();
    }

    @ReactMethod
    public void focus() {
        MainView view = MainView.getInstance();
        view.focusCamera();
    }

    @ReactMethod
    public void cleanup(){
        MainView view = MainView.getInstance();
        view.cleanupCamera();
    }

    @ReactMethod
    public void capture(){
        MainView view = MainView.getInstance();
        view.capture();
    }
}
