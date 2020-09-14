package com.example.covidalertapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covidalertapp.R;
import com.example.covidalertapp.room.DataRepository;
import com.example.covidalertapp.room.NoteViewModel;
import com.example.covidalertapp.room.SampleData;
import com.example.covidalertapp.util.ListStorage;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
/*import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;*/

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;

public class StatisticFragment extends Fragment  implements View.OnClickListener{

    private static final String TAG = "StatisticFragment";
    //widgets
    private LineChart mLineChart;
    private Button filter_button, done_button;
    private FrameLayout layout;
    private Spinner spinner_state, spinner_gender, spinner_age;
    private CardView graphCardView;
    private TextView count_dead, graph_info;
    private View mRootView;
    private TextView death, recovered, hospitalised, total_case;

    //vars
    private NoteViewModel noteViewModel;
    private List<SampleData> sampleData = new ArrayList<>();
    private String filter_state,filter_age,filter_gender;
    private Bitmap bitmap;
    private Button create_pdf_button;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_stats,
                container, false);
        mLineChart = view.findViewById(R.id.line_graphs);
        mRootView = view.findViewById(R.id.const_new_new);
        filter_button = view.findViewById(R.id.button_filter);
        layout = view.findViewById(R.id.frame_container_stats);
        graphCardView = view.findViewById(R.id.cardView2);
        count_dead = view.findViewById(R.id.count_dead_text_view);

        graph_info = view.findViewById(R.id.filter_info);
        death = view.findViewById(R.id.total_deaths);
        recovered = view.findViewById(R.id.recovered_patient);
        hospitalised = view.findViewById(R.id.hospitalized_total);
        total_case = view.findViewById(R.id.total_cases_tv);
        filter_button.setOnClickListener(this);

        Log.d(TAG, "onResume: Called...");
        try {
            setupGraph();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);

        Utils.init(getContext());

        ListStorage storage = ListStorage.getListInstance();
        /*try {
            sampleData = noteViewModel.getAllNotes();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*if(storage.getDeceasedList().size()<=0){
            new somethingAsyncTask(getContext()).execute();
        }*/

        if (sampleData.size() == 0){
            sampleData = storage.getDeceasedList();
        }



        Log.d(TAG, "onCreate: size of sample list : " + sampleData.size());


    }


    public List<Entry> getYAxisData(){
        ListStorage storage = ListStorage.getListInstance();


        if(death.getText().equals("12323") ){
            init();
        }

        Log.d(TAG, "getYAxisData: called");
        List<Entry> yEntry = new ArrayList<>();

        DataRepository repository = new DataRepository(getActivity().getApplication());

        if(filter_age == null && filter_gender == null && filter_state == null){
            filter_gender = "Select Gender";
            filter_state = "Select State";
            filter_age = "Select Age";

            graph_info.setText("Filter : No Filter Applied.");
        }
        int upper = 0;
        int lower= 0;

        switch (filter_age){
            case "Select Age":
                upper = 0;
                lower = 0;
                break;
            case "0-9":
                upper = 9;
                lower = 0;
                break;
            case "10-19":
                upper = 19;
                lower = 10;
                break;
            case "20-29":
                upper = 29;
                lower = 20;
                break;
            case "30-39":
                upper = 39;
                lower = 30;
                break;
            case "40-49":
                upper = 49;
                lower = 40;
                break;
            case "50-59":
                upper = 59;
                lower = 50;
                break;
            case "60-69":
                upper = 69;
                lower = 60;
                break;
            case "70+":
                upper = 139;
                lower = 70;

            default:
                upper =0;
                lower =0;

        }


        Log.d(TAG, "getYAxisData: Filter state : " + filter_state + " gender : " + filter_gender
                + " upper = " + upper +" lower = " + lower);


        repository.getGraphData(filter_state, filter_gender, upper , lower );


        sampleData = storage.getGraphData();
        Log.d(TAG, "getYAxisData: --------------------- size = " + sampleData.size());
        if (sampleData.size() == 0){
            Toast.makeText(getContext(), "No data ", Toast.LENGTH_SHORT).show();
            sampleData = storage.getGraphData();
        }


        for (int i=0; i<sampleData.size(); i++){
            if((sampleData.get(i).getAgeEstimate().equals("null"))
                    || sampleData.get(i).getAgeEstimate().contains("-")){

                Log.d(TAG, "getYAxisData: Null values...");

            }else{
                float age = Float.parseFloat(sampleData.get(i).getAgeEstimate());
                Entry entry = new Entry(i, age  );
                yEntry.add(entry);

            }
        }

        Log.d(TAG, "getYAxisData: yEntry size " + yEntry.size());
        String s = storage.getFilter_info();
        graph_info.setText(s);
        count_dead.setText(""+ storage.getGraphData().size());
        return yEntry;


    }


    private void init(){
        ListStorage storage =ListStorage.getListInstance();

        death.setText(""+storage.getDeceasedList().size());
        recovered.setText(""+storage.getRecoveredList().size());
        hospitalised.setText(""+storage.getHospitalizedList().size());
        int total = storage.getDeceasedList().size()+storage.getRecoveredList().size()
                +storage.getHospitalizedList().size();
        death.setText(""+ total);

    }

    public void setupGraph() throws ExecutionException, InterruptedException {

        Log.d(TAG, "setupGraph: called");
        List<Entry> yAxis = getYAxisData();

        Log.d(TAG, "setupGraph: yAxis Entry =  " + yAxis.size());
        LineDataSet set = new LineDataSet(yAxis, "Total Deceased");
        set.setFillAlpha(110);
        set.setColor(Color.RED);
        set.setLineWidth(3f);
        set.setValueTextSize(10f);
        set.setCircleColor(Color.RED);
        set.setDrawHorizontalHighlightIndicator(false);
        set.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        Log.d(TAG, "setupGraph: dataset size : " + dataSets.size());
        LineData lineData = new LineData(dataSets);

        mLineChart.setData(lineData);

        mLineChart.setBackgroundColor(Color.WHITE);
        mLineChart.setGridBackgroundColor(Color.WHITE);
        mLineChart.refreshDrawableState();

        mLineChart.invalidate();
        Log.d(TAG, "setupGraph: Complete..");


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){

           case R.id.button_filter:
               AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
               final AlertDialog dialog ;
               final View myView = LayoutInflater.from(getContext()).inflate(R.layout.pop_up_window, null);
               done_button  = myView.findViewById(R.id.button_popup_done);
               spinner_age  = myView.findViewById(R.id.spinner_age);
               spinner_gender  = myView.findViewById(R.id.spinner_gender);
               spinner_state  = myView.findViewById(R.id.spinner_state);
               create_pdf_button  = myView.findViewById(R.id.button_popup_create_pdf);

               builder.setView(myView);

               dialog = builder.create();
               dialog.show();

               ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                       R.array.states, android.R.layout.simple_spinner_item );
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinner_state.setAdapter(adapter);
               spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       String text = parent.getItemAtPosition(position).toString();
                       filter_state = text;
                       Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });

               ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                       R.array.age, android.R.layout.simple_spinner_item );
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinner_age.setAdapter(adapter1);
               spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       String text = parent.getItemAtPosition(position).toString();
                       filter_age = text;
                       Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });

               ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                       R.array.gender, android.R.layout.simple_spinner_item );
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinner_gender.setAdapter(adapter2);
               spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       String text = parent.getItemAtPosition(position).toString();
                       filter_gender = text;
                       Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });

               
               done_button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Toast.makeText(getContext(), "Hey..", Toast.LENGTH_SHORT).show();
                       dialog.cancel();

                       Log.d(TAG, "onClick: filter _ state = " + filter_state);
                       Log.d(TAG, "onClick: filter _ age = " + filter_age);
                       Log.d(TAG, "onClick: filter _ gender = " + filter_gender);

                       try {
                           setupGraph();
                       } catch (ExecutionException e) {
                           e.printStackTrace();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               });

               create_pdf_button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Toast.makeText(getContext(), "Does Nothing.", Toast.LENGTH_SHORT).show();
                   }
               });
               break;
       }
    }








}
