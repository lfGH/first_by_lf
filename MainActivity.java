package com.sun.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //所要申请的权限CONTACTS
        String[] perms = {Manifest.permission.GET_ACCOUNTS};

        if (EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限啊打发打发的

        if (EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限啊打发打发的发顺丰阿凡达

            /*Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+123));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/
            Toast.makeText(this,"已获取权限"+perms,Toast.LENGTH_LONG).show();
        } else {
            //第二个参数是被拒绝后再次申请该权限的解释
            //第三个参数是请求码
            //第四个参数是要申请的权限
            Toast.makeText(this,"必要的权限",Toast.LENGTH_LONG).show();
            EasyPermissions.requestPermissions(this, "必要的权限", 0, perms);
        }

        /*setPermissions();
        readContacts();*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
    //分别返回授权成功和失败的权限
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(this,"获取成功的权限"+perms,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this,"获取失败的权限"+perms,Toast.LENGTH_LONG).show();
    }







    static final String[] PERMISSION = new String[]{
            Manifest.permission.GET_ACCOUNTS,// 写入权限
                 //读取设备信息
    };

    /**
     * 设置Android6.0的权限申请
     */
    private void setPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            //Android 6.0申请权限
            Toast.makeText(this,"bbb",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,PERMISSION,1);
        }else {
            Toast.makeText(this, "aaa", Toast.LENGTH_LONG).show();

        }}
    private void readContacts() { Cursor cursor = null;

        try {

// 查询联系人数据

            cursor = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            while (cursor.moveToNext()) {

// 获取联系人姓名

                String displayName = cursor.getString(cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

// 获取联系人手机号

                String number = cursor.getString(cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));
                Toast.makeText(this, ""+number, Toast.LENGTH_LONG).show();

            }

        } catch (Exception e) {

            e.printStackTrace();



        } finally {

            if (cursor != null) {

                cursor.close();

            }

        }

    }



}
