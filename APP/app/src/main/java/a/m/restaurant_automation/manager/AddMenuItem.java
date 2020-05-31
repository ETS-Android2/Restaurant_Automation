package a.m.restaurant_automation.manager;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.model.AppStaticData;
import a.m.restaurant_automation.requestModel.AddMenuItemRequestModel;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IUserService;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddMenuItem extends AppCompatActivity implements View.OnClickListener {
    EditText editText_itemName, editText_itemPrice, editText_itemDescription, editText_addQuantity;
    Spinner spinner_category;
    Button button_addItem;
    private int mUserType = AppStaticData.USERTYPE_MANAGER;



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
        Set<String> set = AppStaticData.categories.keySet();
        selectCategory.add(0,"Select Category");
        selectCategory.addAll(set);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(AddMenuItem.this,android.R.layout.simple_spinner_item,selectCategory);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(categoryAdapter);
       // spinner_category.setOnItemClickListener(this);
        button_addItem.setOnClickListener(this);


        

    }


    @Override
    public void onClick(View v) {
        String spinnerItemId = spinner_category.getSelectedItem().toString();
        int id =0;
        if (!checkEmptyFields()) {
            if (AppStaticData.categories.containsKey(spinnerItemId)) {
                id = AppStaticData.categories.get(spinnerItemId);
                String itemName = editText_itemName.getText().toString();
                double itemPrice = Double.parseDouble(editText_itemPrice.getText().toString());
                String itemDescription = editText_itemDescription.getText().toString();
                int itemQuantity = Integer.parseInt(editText_addQuantity.getText().toString());
                AddMenuItem(itemName, itemPrice, itemDescription, itemQuantity, id, mUserType);
            }
        }
        else {
            Snackbar.make(v,"Please select category", BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }
    public boolean checkEmptyFields(){
        if (TextUtils.isEmpty(editText_itemName.getText().toString())){
            editText_itemName.setError("Item Name Cannot be Empty!");
            editText_itemName.requestFocus();
            return true;
        }
        else if (TextUtils.isEmpty(editText_itemPrice.getText().toString())){
            editText_itemPrice.setError("Item Price Cannot be Empty!");
            editText_itemPrice.requestFocus();
            return true;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(editText_itemDescription.getText().toString()).matches()){
            editText_itemDescription.setError("Item Description cannot be Empty!");
            editText_itemDescription.requestFocus();
            return true;
        }
        else if (TextUtils.isEmpty(editText_addQuantity.getText().toString())){
            editText_addQuantity.setError("Item Quantity Cannot be Empty!");
            editText_addQuantity.requestFocus();
            return true;
        }
        return false;
    }

    public void AddMenuItem(String menuItemName, double menuItemPrice, String menuItemDescription, int menuItemQuantity, int categoryId,int createdBy){
        IUserService userService = RetrofitClient.getRetrofitInstance().create(IUserService.class);
        AddMenuItemRequestModel addMenuItemRequestModel = new AddMenuItemRequestModel();
        addMenuItemRequestModel.menuItemName = menuItemName;
        addMenuItemRequestModel.price = menuItemPrice;
        addMenuItemRequestModel.menuItemDescription = menuItemDescription;
        addMenuItemRequestModel.availablequantity = menuItemQuantity;
        addMenuItemRequestModel.categoryId = categoryId;
        addMenuItemRequestModel.createdBy = createdBy;

        Call<ResponseModel<MenuItemResponse>> call = userService.addMenuItem(addMenuItemRequestModel);

        call.enqueue(new Callback<ResponseModel<MenuItemResponse>>() {
            @Override
            public void onResponse(Call<ResponseModel<MenuItemResponse>> call, Response<ResponseModel<MenuItemResponse>> response) {
                ResponseModel<MenuItemResponse> responseModel =response.body();

                if (responseModel != null && responseModel.getError() != null && !responseModel.getError().equals("")){
                    Toast.makeText(getApplicationContext(),responseModel.getError().getErrorMessage(),Toast.LENGTH_LONG).show();
                }
                else{
                   // Toast.makeText(getApplicationContext(),  responseModel.getData().getMenuItemId().toString(),Toast.LENGTH_LONG).show();
                    Intent intentToMain =new Intent(AddMenuItem.this, EmployeeMenuItemActivity.class);
                    startActivity(intentToMain);
                    finish();

                }

            }

            @Override
            public void onFailure(Call<ResponseModel<MenuItemResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong not able to add item" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }






}
