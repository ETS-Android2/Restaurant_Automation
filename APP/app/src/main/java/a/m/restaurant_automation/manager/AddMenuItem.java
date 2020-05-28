package a.m.restaurant_automation.manager;

import a.m.restaurant_automation.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddMenuItem extends AppCompatActivity implements AdapterView.OnItemClickListener {
    EditText editText_itemName, editText_itemPrice, editText_itemDescription, editText_addQuantity;
    Spinner spinner_category;
    Button button_addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        editText_itemName = findViewById(R.id.editText_itemName);
        editText_itemPrice = findViewById(R.id.editText_itemPrice);
        editText_itemDescription = findViewById(R.id.editText_itemDescription);
        editText_addQuantity = findViewById(R.id.editText_addQuantity);
        spinner_category = findViewById(R.id.spinner_category);
        button_addItem = findViewById(R.id.button_addItem);

        List<String> selectCategory= new ArrayList<>();
        selectCategory.add(0,"Select Category");
        selectCategory.add(1,"Appetizer");
        selectCategory.add(2,"Main Course");
        selectCategory.add(3,"Beverage");
        selectCategory.add(4,"Dessert");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(AddMenuItem.this,android.R.layout.simple_spinner_item,selectCategory);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(categoryAdapter);
        spinner_category.setOnItemClickListener(this);








    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
