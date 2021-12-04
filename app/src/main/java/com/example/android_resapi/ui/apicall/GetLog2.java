package com.example.android_resapi.ui.apicall;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_resapi.R;
import com.example.android_resapi.httpconnection.GetRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class GetLog2 extends GetRequest {
    final static String TAG = "AndroidAPITest";
    String urlStr;
    ArrayList<Integer> jsonList = new ArrayList<>(); // ArrayList 선언
    ArrayList<String> labelList = new ArrayList<>(); // ArrayList 선언
    BarChart barChart;
    int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0,c8=0,c9=0,c10=0,c11=0,c12=0,c13=0,c14=0,c15=0,c16=0,c17=0,c18=0,c19=0,c20=0,c21=0,c22=0,c23=0,c24=0;


    public GetLog2(Activity activity, String urlStr) {
        super(activity);
        this.urlStr = urlStr;
    }

    @Override
    protected void onPreExecute() {
        try {

            TextView textView_Date1 = activity.findViewById(R.id.textView_date1);
            TextView textView_Date2 = activity.findViewById(R.id.textView_date2);

            String params = String.format("?from=%s:00&to=%s:00",textView_Date1.getText().toString()+"00:00",
                                                            textView_Date2.getText().toString()+"00:00");
            Log.i(TAG,"urlStr="+urlStr+params);
            url = new URL(urlStr+params);

        } catch (MalformedURLException e) {
            Toast.makeText(activity,"URL is invalid:"+urlStr, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        TextView message = activity.findViewById(R.id.message2);
        message.setText("조회중...");
    }

    @Override
    protected void onPostExecute(String jsonString) {
        TextView message = activity.findViewById(R.id.message2);
        if (jsonString == null) {
            message.setText("로그 없음");
            return;
        }
        message.setText("");
        ArrayList<Tag> arrayList = getArrayListFromJSONString(jsonString);
        int time_chart=0;
        barChart = (BarChart)activity.findViewById(R.id.barChart);

        try{
            for(int i=0;i<arrayList.size();i++){
                Tag s= arrayList.get(i);
                time_chart = Integer.parseInt(s.toString().substring(12,14));

                if(time_chart==1){
                    c1++;
                }else if(time_chart==2){
                    c2++;
                }else if(time_chart==3){
                    c3++;
                }else if(time_chart==4){
                    c4++;
                }else if(time_chart==5){
                    c5++;
                }else if(time_chart==6){
                    c6++;
                }else if(time_chart==7){
                    c7++;
                }else if(time_chart==8){
                    c8++;
                }else if(time_chart==9){
                    c9++;
                }else if(time_chart==10){
                    c10++;
                }else if(time_chart==11){
                    c11++;
                }else if(time_chart==12){
                    c12++;
                }else if(time_chart==13){
                    c13++;
                }else if(time_chart==14){
                    c14++;
                }else if(time_chart==15){
                    c15++;
                }else if(time_chart==16){
                    c16++;
                }else if(time_chart==17){
                    c17++;
                }else if(time_chart==18){
                    c19++;
                }else if(time_chart==19){
                    c19++;
                }else if(time_chart==20){
                    c20++;
                }else if(time_chart==21){
                    c21++;
                }else if(time_chart==22){
                    c22++;
                }else if(time_chart==23){
                    c23++;
                }else if(time_chart==24 || time_chart==0){
                    c24++;
                }
            }
            graphInitSetting();       //그래프 기본 세팅
            BarChartGraph(labelList, jsonList);
            barChart.getAxisRight().setAxisMaxValue(80);
            barChart.getAxisLeft().setAxisMaxValue(80);


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    protected ArrayList<Tag> getArrayListFromJSONString(String jsonString) {
        ArrayList<Tag> output = new ArrayList();
        try {
            // 처음 double-quote와 마지막 double-quote 제거
            jsonString = jsonString.substring(1,jsonString.length()-1);
            // \\\" 를 \"로 치환
            jsonString = jsonString.replace("\\\"","\"");

            Log.i(TAG, "jsonString="+jsonString);

            JSONObject root = new JSONObject(jsonString);
            JSONArray jsonArray = root.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject)jsonArray.get(i);

                Tag thing = new Tag(jsonObject.getString("temperature"),
                                    jsonObject.getString("LED"),
                                    jsonObject.getString("timestamp"));

                output.add(thing);
            }

        } catch (JSONException e) {
            //Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }

    class Tag {
        String temperature;
        String LED;
        String timestamp;

        public Tag(String temp, String led, String time) {
            temperature = temp;
            LED = led;
            timestamp = time;
        }

        public String toString() {
            return String.format("[%s] Temperature: %s, LED: %s", timestamp, temperature, LED);
        }
    }

    public void graphInitSetting(){

        labelList.add("1H");
        labelList.add("2H");
        labelList.add("3H");
        labelList.add("4H");
        labelList.add("5H");
        labelList.add("6H");
        labelList.add("7H");
        labelList.add("8H");
        labelList.add("9H");
        labelList.add("10H");
        labelList.add("11H");
        labelList.add("12H");
        labelList.add("13H");
        labelList.add("14H");
        labelList.add("15H");
        labelList.add("16H");
        labelList.add("17H");
        labelList.add("18H");
        labelList.add("19H");
        labelList.add("20H");
        labelList.add("21H");
        labelList.add("22H");
        labelList.add("23H");
        labelList.add("24H");


        jsonList.add(c1);
        jsonList.add(c2);
        jsonList.add(c3);
        jsonList.add(c4);
        jsonList.add(c5);
        jsonList.add(c6);
        jsonList.add(c7);
        jsonList.add(c8);
        jsonList.add(c9);
        jsonList.add(c10);
        jsonList.add(c11);
        jsonList.add(c12);
        jsonList.add(c13);
        jsonList.add(c14);
        jsonList.add(c15);
        jsonList.add(c16);
        jsonList.add(c17);
        jsonList.add(c18);
        jsonList.add(c19);
        jsonList.add(c20);
        jsonList.add(c21);
        jsonList.add(c22);
        jsonList.add(c23);
        jsonList.add(c24);



        BarChartGraph(labelList, jsonList);
        //barChart.setRendererLeftYAxis();
//        barChart.setMaxVisibleValueCount(50);
//        barChart.setTop(50);
//        barChart.setBottom(0);
//        barChart.setAutoScaleMinMaxEnabled(true);
        barChart.getAxisRight().setAxisMaxValue(80);
        barChart.getAxisLeft().setAxisMaxValue(80);
    }
    /**
     * 그래프함수
     */
    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {
        // BarChart 메소드


        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valList.size(); i++) {
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        BarDataSet depenses = new BarDataSet(entries, "시간대별 위험 발생 횟수"); // 변수로 받아서 넣어줘도 됨
        depenses.setAxisDependency(YAxis.AxisDependency.LEFT);
        barChart.setDescription(" ");

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < labelList.size(); i++) {
            labels.add((String) labelList.get(i));
        }

        BarData data = new BarData(labels, depenses); // 라이브러리 v3.x 사용하면 에러 발생함
        depenses.setColors(ColorTemplate.LIBERTY_COLORS); //

        barChart.setData(data);
        barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }



}

