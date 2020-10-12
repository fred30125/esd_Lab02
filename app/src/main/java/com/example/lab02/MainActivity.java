package com.example.lab02;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_alert;
    Button btn_check;
    Button btn_single_sel;
    Button btn_multi_sel;
    TextView output;
    private AlertDialog dialog;
    String[] items = {"Android", "iOS", "Windows Phone", "Firefox OS"};
    boolean[] itemsChecked = new boolean[items.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_alert=findViewById(R.id.btn_alert);
        btn_check=findViewById(R.id.btn_check);
        btn_single_sel=findViewById(R.id.btn_single_sel);
        btn_multi_sel=findViewById(R.id.btn_multi_sel);
        output = findViewById(R.id.txt_output);
        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("關於");
                builder.setMessage("版本: 4.4.2版\n課程:嵌入式軟體設計");
                builder.setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface
                                                        dialoginterface, int i) {
                                // 不作任何事
                            }
                        });
                builder.show();

            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("確認")
                        .setMessage("確認結束本程式?")
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface
                                                                dialoginterface, int i) {
                                        finish();
                                    } })
                        .setNegativeButton("取消", null)
                        .show();

            }
        });

        btn_single_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                String[] options = {"紅色", "黃色", "綠色" };
                builder.setItems(options, listener);
                builder.setNegativeButton("取消", null);
                dialog = builder.create();
                dialog.show();

            }
        });


        btn_multi_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("請勾選選項?")
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        String msg = "";
                                        for (int index = 0; index < items.length; index++) {
                                            if (itemsChecked[index])
                                                msg += items[index] + "\n";
                                        }
                                        output.setText(msg);
                                    }
                                })
                        .setNegativeButton("取消", null)
                        .setMultiChoiceItems(items,itemsChecked,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        Toast.makeText(MainActivity.this,
                                                items[which] + (isChecked ? " 勾選" : "沒有勾選"),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .create();
                dialog.show();


            }
        });
    }





    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // 找到Button元件
            // 指定背景色彩
            switch(which){
                case 0: btn_single_sel.setBackgroundColor(Color.RED);
                    break;
                case 1: btn_single_sel.setBackgroundColor(Color.YELLOW);
                    break;
                case 2: btn_single_sel.setBackgroundColor(Color.GREEN);
                    break;
            }
        }
    };

}
