package com.example.rasi.SmartTshirts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;



public class Search extends Activity {

    private ListView lv;

    // Search EditText
    EditText inputSearch;

    // ArrayList for Listview
    private List<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv = (ListView) findViewById(R.id.lvSearch);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        mProductList = ShoppingCartHelper.getCatalog(getResources());

        // Adding items to listview

        lv.setAdapter(new ProductAdapter(mProductList, getLayoutInflater(), false));


    }


}
