package com.gui.abc.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNormalDialog;
    private Button btnListDialog;
    private Button btnSingleChoiceDialog;
    private Button btnMultiChoiceDialog;
    private Button btnProgressDialog;
    private Button btnEditDialog;
    private Button btnCustomDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView() {
        btnNormalDialog = (Button) findViewById(R.id.btn_normal_dialog);
        btnListDialog = (Button) findViewById(R.id.btn_list_dialog);
        btnSingleChoiceDialog = (Button) findViewById(R.id.btn_single_choice_dialog);
        btnMultiChoiceDialog = (Button) findViewById(R.id.btn_multi_choice_dialog);
        btnProgressDialog = (Button) findViewById(R.id.btn_progress_dialog);
        btnEditDialog = (Button) findViewById(R.id.btn_edit_dialog);
        btnCustomDialog = (Button) findViewById(R.id.btn_custom_dialog);

        btnNormalDialog.setOnClickListener(this);
        btnListDialog.setOnClickListener(this);
        btnSingleChoiceDialog.setOnClickListener(this);
        btnMultiChoiceDialog.setOnClickListener(this);
        btnProgressDialog.setOnClickListener(this);
        btnEditDialog.setOnClickListener(this);
        btnCustomDialog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal_dialog:
                showNormalDialog();
                break;
            case R.id.btn_list_dialog:
                showListDialog();
                break;
            case R.id.btn_single_choice_dialog:
                showSingleChoiceDialog();
                break;
            case R.id.btn_multi_choice_dialog:
                showMultiChoiceDialog();
                break;
            case R.id.btn_progress_dialog:
                showProgressDialog();
                break;
            case R.id.btn_edit_dialog:
                showEditDialog();
                break;
            case R.id.btn_custom_dialog:
                showCustomDialog();
                break;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog, null);
        builder.setView(layout).create().show();

    }

    private void showEditDialog() {
        final EditText editText = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("编辑对话框");
        builder.setMessage("这是一个编辑对话框");
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = editText.getText().toString();
                showText(input);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void showProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        final int MAX_PROGRESS = 100;
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("提示");
        dialog.setMessage("这是一个进度条对话框");
        dialog.setProgress(0);
        //监听取消时间
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showText("进度条结束");
            }
        });
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX_PROGRESS) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress++;
                    //非UI线程，可以处理DialogUI
                    dialog.setProgress(progress);
                }
                dialog.cancel();
            }
        }).start();

    }


    private void showMultiChoiceDialog() {
        final String[] items = new String[]{"上海", "北京", "湖南", "湖北", "海南"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("简单列表对话框");
        //千万不要加这句，不然列表显示不出来
//        builder.setMessage("这是一个简单的列表对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items, new boolean[]{false, false, false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                showText(items[which]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText("确定");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText("取消");
            }
        });

        builder.create().show();
    }

    private void showSingleChoiceDialog() {
        final String[] items = new String[]{"上海", "北京", "湖南", "湖北", "海南"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("简单列表对话框");
        //千万不要加这句，不然列表显示不出来
//        builder.setMessage("这是一个简单的列表对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText(items[which]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText("确定");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText("取消");
            }
        });

        builder.create().show();
    }

    private void showListDialog() {
        final String[] items = new String[]{"上海", "北京", "湖南", "湖北", "海南"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("简单列表对话框");
        //千万不要加这句，不然列表显示不出来
//        builder.setMessage("这是一个简单的列表对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText(items[which]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText("确定");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText("取消");
            }
        });

        builder.create().show();
    }

    private void showNormalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("简单对话框")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("这是一个简单对话框")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //定义自己想要做的操作
                        showText("确定");
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showText("取消");
            }
        });
        builder.create().show();
    }

    public void showText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
