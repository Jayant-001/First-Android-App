package com.example.messedup.ebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.messedup.R;

import java.util.ArrayList;

public class ManageEbooks extends AppCompatActivity {

    Button continueBtn;
    Spinner sp_branch, sp_sub;
    ArrayList<String> list_branch, list_cse, list_ec, list_ic, list_it;
    ArrayAdapter<String> adapter_branch, adapter_sub;

    private String selected_branch = "", selected_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ebooks);

        continueBtn = findViewById(R.id.btn_continue);
        sp_branch = findViewById(R.id.sp_branch);
        sp_sub = findViewById(R.id.sp_sub);

        list_branch = new ArrayList<>();
        list_branch.add("Select branch");
        list_branch.add("CSE");
        list_branch.add("IC");
        list_branch.add("EC");
        list_branch.add("IT");


        adapter_branch = new ArrayAdapter<>(getApplicationContext(), R.layout.text_blue, list_branch);
        sp_branch.setAdapter(adapter_branch);


        // ---------------------------------------------------------CSE list---------------------------------------------
        list_cse = new ArrayList<>();
        list_cse.add("Math-I");
        list_cse.add("Communication-I");
        list_cse.add("Physics-I");
        list_cse.add("Chemistry");
        list_cse.add("F C I T");
        list_cse.add("Technical Drawing");
        list_cse.add("Workshop Practice");

        list_cse.add("Math-II");
        list_cse.add("Physics-II");
        list_cse.add("B E E E");
        list_cse.add("Multimedia & Animation");
        list_cse.add("Programming using C");
        list_cse.add("O A T");

        list_cse.add("Math-III");
        list_cse.add("I & W T");
        list_cse.add("EVS");
        list_cse.add("D C C N");
        list_cse.add("DS using C");
        list_cse.add("Digital Electronic");

        list_cse.add("Communication-II");
        list_cse.add("D B M S");
        list_cse.add("OOP using Java");
        list_cse.add("O S");
        list_cse.add("E-com");
        list_cse.add("Energy Conservation");
        list_cse.add("U H V");

        list_cse.add("Software Engineering");
        list_cse.add("Web Dev using PHP");
        list_cse.add("Python");
        list_cse.add("C A H M");
        list_cse.add("I O T");

        list_cse.add("Android Development");
        list_cse.add("Cloud Computing");
        list_cse.add("I M E D");
        list_cse.add("Advance Java");
        list_cse.add("M L & D S");
        list_cse.add(".NET");

        list_cse.add("Previous Year Paper");
        list_cse.add("Other");


        // ------------------------------------------------------EC list------------------------------------------------------------
        list_ec = new ArrayList<>();

        list_ec.add("Math-I");
        list_ec.add("Communication-I");
        list_ec.add("Physics-I");
        list_ec.add("Chemistry");
        list_ec.add("Engineering Drawing");
        list_ec.add("E M M");
        list_ec.add("Workshop-I");

        list_ec.add("Math-II");
        list_ec.add("Physics-II");
        list_ec.add("B I T");
        list_ec.add("EE-I");
        list_ec.add("E C D");
        list_ec.add("Workshop-II");

        list_ec.add("Math-III");
        list_ec.add("EE-II");
        list_ec.add("E V S");
        list_ec.add("E D C");
        list_ec.add("Electronic Workshop");
        list_ec.add("Digital Electronic");
        list_ec.add("U H V");

        list_ec.add("Communication-II");
        list_ec.add("I E T");
        list_ec.add("N F T L");
        list_ec.add("E I M");
        list_ec.add("P C E");
        list_ec.add("Energy Conservation");

        list_ec.add("I M E D");
        list_ec.add("Microprocessor");
        list_ec.add("O F C");
        list_ec.add("Consumer Electronic");
        list_ec.add("Programming in C");

        list_ec.add("Microwave");
        list_ec.add("Microcontrollers");
        list_ec.add("W M C S");
        list_ec.add("Control System");
        list_ec.add("Medical Electronics");
        list_ec.add("Computer Networks");

        list_ec.add("Previous Year Paper");
        list_ec.add("Other");

        // -------------------------------------------------IC list----------------------------------------------------------------
        list_ic = new ArrayList<>();

        list_ic.add("Communication-I");
        list_ic.add("Math-I");
        list_ic.add("Physics-I");
        list_ic.add("Chemistry");
        list_ic.add("Engineering Drawing");
        list_ic.add("E M M");
        list_ic.add("Workshop-I");

        list_ic.add("Math-II");
        list_ic.add("Physics-II");
        list_ic.add("B I T");
        list_ic.add("EE-I");
        list_ic.add("E C D");
        list_ic.add("Workshop-II");

        list_ic.add("Math-III");
        list_ic.add("EE-II");
        list_ic.add("E V S");
        list_ic.add("E D C");
        list_ic.add("Electronic Workshop");
        list_ic.add("Transducer & Application");
        list_ic.add("U H V");

        list_ic.add("Communication-II");
        list_ic.add("Principles of DE");
        list_ic.add("N F T L");
        list_ic.add("E I M");
        list_ic.add("Process Instrumentation");
        list_ic.add("Energy Conservation");

        list_ic.add("I M E D");
        list_ic.add("Microprocessor");
        list_ic.add("Industrial Control");
        list_ic.add("S T R D");
        list_ic.add("Programming in C");

        list_ic.add("Process Control");
        list_ic.add("Microcontrollers");
        list_ic.add("B M I");
        list_ic.add("Specialised Instruments");
        list_ic.add("Robotics");
        list_ic.add("Neural Networks");

        list_ic.add("Previous Year Paper");
        list_ic.add("Other");


        // -------------------------------------------------------------IT list------------------------------------------------------
        list_it = new ArrayList<>();

        list_it.add("Communication-I");
        list_it.add("Math-I");
        list_it.add("Physics-I");
        list_it.add("Chemistry");
        list_it.add("F C I T");
        list_it.add("Technical Drawing");
        list_it.add("Workshop Practice");

        list_it.add("Math-II");
        list_it.add("Physics-II");
        list_it.add("B E E E");
        list_it.add("Multimedia & Animation");
        list_it.add("Programming using C");
        list_it.add("O A T");

        list_it.add("Math-III");
        list_it.add("I & W T");
        list_it.add("E V S");
        list_it.add("D C C N");
        list_it.add("DS using C");
        list_it.add("C A H M");

        list_it.add("Communication-II");
        list_it.add("D B M S");
        list_it.add("OOP using Java");
        list_it.add("O S");
        list_it.add("E-com");
        list_it.add("Energy Conservation");
        list_it.add("U H V");

        list_it.add("Software Engineering");
        list_it.add("Web Dev using PHP");
        list_it.add("Python");
        list_it.add("IS & ITLaws");
        list_it.add("I O T");

        list_it.add("Android Development");
        list_it.add("Cloud Computing");
        list_it.add("I M E D");
        list_it.add("Advance Java");
        list_it.add("Big Data");
        list_it.add("M L & D S");
        list_it.add("Digital Image Processing");

        list_it.add("Previous Year Paper");
        list_it.add("Other");


        // branch selected listener
        sp_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1) {
                    adapter_sub = new ArrayAdapter<>(getApplicationContext(), R.layout.text_blue, list_cse);
                    sp_sub.setAdapter(adapter_sub);
//                    selected_branch = "CSE";
                } else if (position == 2) {
                    adapter_sub = new ArrayAdapter<>(getApplicationContext(), R.layout.text_blue, list_ic);
                    sp_sub.setAdapter(adapter_sub);
//                    selected_branch = "IC";
                } else if (position == 3) {
                    adapter_sub = new ArrayAdapter<>(getApplicationContext(), R.layout.text_blue, list_ec);
                    sp_sub.setAdapter(adapter_sub);
//                    selected_branch = "EC";
                } else if (position == 4) {
                    adapter_sub = new ArrayAdapter<>(getApplicationContext(), R.layout.text_blue, list_it);
                    sp_sub.setAdapter(adapter_sub);
//                    selected_branch = "IC";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sp_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                TextView t = (TextView)sp_sub.getSelectedView();
//                selected_sub = t.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView branch_text = ((TextView)sp_branch.getSelectedView());
                TextView subject_text = (TextView)sp_sub.getSelectedView();

                selected_branch = branch_text.getText().toString();
                selected_sub = subject_text.getText().toString();

//                Log.d("jayant", selected_branch);
//                Log.d("jayant", selected_sub);


                if(selected_branch.equals("")) {

                    branch_text.setError("");
                    sp_branch.requestFocus();
                    return;
                }

                Intent intent = new Intent(ManageEbooks.this, UploadEbooks.class);
                intent.putExtra("branch", selected_branch);
                intent.putExtra("subject", selected_sub);
                startActivity(intent);
            }
        });


    }
}