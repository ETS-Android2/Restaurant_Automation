package a.m.restaurant_automation.customer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.chef.ChefDashBoardItemsAdapter;
import a.m.restaurant_automation.requestModel.ReadyForPaymentRequestModel;
import a.m.restaurant_automation.responseModel.GetOrderResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.responseModel.StatusCheckResponse;
import a.m.restaurant_automation.service.IDataService;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapterCustomer extends RecyclerView.Adapter<OrderAdapterCustomer.ViewHolder> {

    private ArrayList<GetOrderResponseModel> getOrderResponseModel;
    Context context;
    int size = 0;
    AlertDialog.Builder alertDialogPayment;
    private final Handler handler=new Handler();

    public OrderAdapterCustomer(ArrayList<GetOrderResponseModel> getOrderResponseModel, Context context) {
        this.getOrderResponseModel = getOrderResponseModel;
        size = this.getOrderResponseModel.size();
        this.context = context;
    }
    @NonNull
    @Override
    public OrderAdapterCustomer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customer_order_history,parent,false);
      context = parent.getContext();
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderAdapterCustomer.ViewHolder holder, final int position) {
        holder.textView_FirstName.setText(""+getOrderResponseModel.get(position).firstName);
        holder.textView_tableNumber.setText(""+getOrderResponseModel.get(position).tableId);
        holder.textView_orderNumber.setText(""+getOrderResponseModel.get(position).orderId);
        holder.textView_orderStatus.setText("Status: "+getOrderResponseModel.get(position).orderStatusTitle);
        if(getOrderResponseModel.get(position).isPaid)
        {
            holder.payNow.setText("Bill Amount : " + getOrderResponseModel.get(position).billingAmount + " $");
        }
        else if(!getOrderResponseModel.get(position).isPaid){

                holder.payNow.setText("Pay Now ( " + getOrderResponseModel.get(position).billingAmount + " $ )");

        }

        ChefDashBoardItemsAdapter itemsAdapter = new ChefDashBoardItemsAdapter(getOrderResponseModel.get(position).menuItems);
        holder.recyclerViewItems.setAdapter(itemsAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.recyclerViewItems.setLayoutManager(layoutManager);
        ViewGroup.LayoutParams layoutParams = holder.recyclerViewItems.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        holder.recyclerViewItems.setLayoutParams(layoutParams);


        /*holder.expandCollapseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams layoutParams = holder.recyclerViewItems.getLayoutParams();
                if (layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    holder.recyclerViewItems.setLayoutParams(layoutParams);
                    holder.expandCollapseBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    layoutParams.height = 90;
                    holder.recyclerViewItems.setLayoutParams(layoutParams);
                    holder.expandCollapseBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }

            }
        });*/


        holder.payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v) {

                if(!getOrderResponseModel.get(position).isPaid) {
                    alertDialogPayment = new AlertDialog.Builder(context);
                    alertDialogPayment.setTitle("Payment")
                            .setMessage("Do you want to pay the bill and checkout?")
                            .setPositiveButton("Pay Now", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(getOrderResponseModel.get(position).statusId==3) {
                                        int orderId = getOrderResponseModel.get(position).orderId;
                                        payTheBill(orderId);
                                        //holder.payNow.setText("Bill Amount ( " + getOrderResponseModel.get(position).billingAmount + " $ )");
                                        holder.payNow.setVisibility(View.INVISIBLE);
                                    }
                                    else if(getOrderResponseModel.get(position).statusId!=3){
                                        Toast.makeText(context,"Your order is not completed yet",Toast.LENGTH_LONG).show();
                                    }
                                }
                            })
                            .setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create();
                    alertDialogPayment.show();
                }
                else if(getOrderResponseModel.get(position).isPaid) {
                    Toast.makeText(context,"You Paid "+getOrderResponseModel.get(position).billingAmount + " $ on " + getOrderResponseModel.get(position).orderDate.substring(0,10),Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_tableNumber, textView_orderNumber, textView_FirstName, textView_orderStatus;
        Button payNow,expandCollapseBtn;
        RecyclerView recyclerViewItems;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_FirstName = itemView.findViewById(R.id.customerName);
            textView_orderNumber = itemView.findViewById(R.id.textView_orderNumberCustomer);
            textView_tableNumber = itemView.findViewById(R.id.textView_tableNumberCustomer);
            textView_orderStatus = itemView.findViewById(R.id.orderStatusCustomer);
            payNow = itemView.findViewById(R.id.payNow);
            //expandCollapseBtn = itemView.findViewById(R.id.buttonArrowDown);
            recyclerViewItems = itemView.findViewById(R.id.recyclerView_customerOrderHistory);
        }
    }

    public void payTheBill(int orderId){

        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        ReadyForPaymentRequestModel readyForPaymentRequestModel = new ReadyForPaymentRequestModel();
        readyForPaymentRequestModel.orderId = orderId;

        Call<ResponseModel<StatusCheckResponse>> call = dataService.readyForPayment(readyForPaymentRequestModel);
        call.enqueue(new Callback<ResponseModel<StatusCheckResponse>>() {
            @Override
            public void onResponse(Call<ResponseModel<StatusCheckResponse>> call, Response<ResponseModel<StatusCheckResponse>> response) {
                ResponseModel<StatusCheckResponse> responseModel = response.body();
                if (responseModel != null) {
                    if (responseModel.getError() != null) {
                        Toast.makeText(context, responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context,responseModel.getData().statusMessage + "Thank You!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<StatusCheckResponse>> call, Throwable t) {
                Toast.makeText(context, "something went wrong! Order Has not been Placed" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
