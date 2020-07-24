package com.documentscanner.helpers;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.core.CvType;

/**
 * Created by allgood on 05/03/16.
 */
public class ScannedDocument {
    public Mat original;
    public Mat processed;
    public Quadrilateral quadrilateral;
    public Point[] previewPoints;
    public Size previewSize;
    public Size originalSize;
    public Point[] originalPoints;
    public int heightWithRatio;
    public int widthWithRatio;

    public ScannedDocument(Mat original) {
        // this.original = new Mat(original.size(), CvType.CV_8UC4);
        // original.copyTo(this.original);
        this.original = original.clone();
    }

    public Mat getProcessed() {
        return processed;
    }

    public ScannedDocument setProcessed(Mat processed) {
        this.processed = processed;
        return this;
    }

    public WritableMap previewPointsAsHash() {
        if (this.previewPoints == null) return null;
        WritableMap rectangleCoordinates = new WritableNativeMap();
        double xRatio = this.originalSize.height / this.widthWithRatio;
        double yRatio = this.originalSize.width / this.heightWithRatio;


        WritableMap topLeft = new WritableNativeMap();
        topLeft.putDouble("x", this.originalPoints[0].x * xRatio);
        topLeft.putDouble("y", this.originalPoints[0].y * yRatio);

        WritableMap topRight = new WritableNativeMap();
        topRight.putDouble("x", this.originalPoints[1].x * xRatio);
        topRight.putDouble("y", this.originalPoints[1].y * yRatio);

        WritableMap bottomRight = new WritableNativeMap();
        bottomRight.putDouble("x", this.originalPoints[2].x * xRatio);
        bottomRight.putDouble("y", this.originalPoints[2].y * yRatio);

        WritableMap bottomLeft = new WritableNativeMap();
        bottomLeft.putDouble("x", this.originalPoints[3].x * xRatio);
        bottomLeft.putDouble("y", this.originalPoints[3].y * yRatio);

        rectangleCoordinates.putMap("topLeft", topLeft);
        rectangleCoordinates.putMap("topRight", topRight);
        rectangleCoordinates.putMap("bottomRight", bottomRight);
        rectangleCoordinates.putMap("bottomLeft", bottomLeft);

        return rectangleCoordinates;
    }

    public void release() {
        if (processed != null) {
            processed.release();
        }
        if (original != null) {
            original.release();
        }

        if (quadrilateral != null && quadrilateral.contour != null) {
            quadrilateral.contour.release();
        }
    }
}
