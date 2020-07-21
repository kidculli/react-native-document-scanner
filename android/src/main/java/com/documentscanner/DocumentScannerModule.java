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

    // @ReactMethod
    // public void capture(final int viewTag) {
    //     final ReactApplicationContext context = getReactApplicationContext();
    //     UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
    //     uiManager.addUIBlock(new UIBlock() {
    //         @Override
    //         public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
    //             try {
    //                 MainView view = (MainView) nativeViewHierarchyManager.resolveView(viewTag);
    //                 view.capture();
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     });
    // }
    
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

    @ReactMethod
    public WritableMap applyImageFilter(String image, String filter){
        // TODO: set base64 image
        // apply filter
        // return images

        /**
         * return {
         *   image: base64 filteredImage
         * }
         */

        return null;
    }
}
