package com.example.shubham_pc.aptitudecracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

//TODO: Generate Graphs by passing the user's score points as co=ordinates
// This is Static implementation
public class Result extends AppCompatActivity {
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        graphView = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]
                {
                        new DataPoint(0, 1),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6),
                        new DataPoint(5, 4),
                        new DataPoint(6, 10),

                });
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[]
                {
                        new DataPoint(0, 15),
                        new DataPoint(1, 5),
                        new DataPoint(2, 13),
                        new DataPoint(3, 21),
                        new DataPoint(4, 16),
                        new DataPoint(5, 20),
                        new DataPoint(6, 10),

                });
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>();

        series1.setColor(Color.parseColor("#ee0000"));
        graphView.addSeries(series);
        graphView.addSeries(series1);

    }
}
