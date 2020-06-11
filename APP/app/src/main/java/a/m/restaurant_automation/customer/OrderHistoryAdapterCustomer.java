package a.m.restaurant_automation.customer;

import android.content.Context;
import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.chef.ChefDashBoardItemsAdapter;
import a.m.restaurant_automation.manager.AddEmployeeAdapter;
import a.m.restaurant_automation.responseModel.GetOrderResponseModel;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderHistoryAdapterCustomer extends RecyclerView.Adapter<OrderHistoryAdapterCustomer.ViewHolder> {

    private ArrayList<GetOrderResponseModel> getOrderResponseModel;
    private  Context context;
    int size = 0;

    public OrderHistoryAdapterCustomer(ArrayList<GetOrderResponseModel> getOrderResponseModel, Context context) {
        this.getOrderResponseModel = getOrderResponseModel;
        size = this.getOrderResponseModel.size();
        this.context = context;
    }
    @NonNull
    @Override
    public OrderHistoryAdapterCustomer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customer_order_history,parent,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderHistoryAdapterCustomer.ViewHolder holder, int position) {
        holder.textView_FirstName.setText(""+getOrderResponseModel.get(position).firstName);
        holder.textView_tableNumber.setText(""+getOrderResponseModel.get(position).tableId);
        holder.textView_orderNumber.setText(""+getOrderResponseModel.get(position).orderId);
        holder.textView_orderStatus.setText("Status: "+getOrderResponseModel.get(position).orderStatusTitle);
        holder.payNow.setText("Pay Now ( "+getOrderResponseModel.get(position).billingAmount +" $ )");

        ChefDashBoardItemsAdapter itemsAdapter = new ChefDashBoardItemsAdapter(getOrderResponseModel.get(position).menuItems);
        holder.recyclerViewItems.setAdapter(itemsAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.recyclerViewItems.setLayoutManager(layoutManager);

        holder.expandCollapseBtn.setOnClickListener(new View.OnClickListener() {
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
            expandCollapseBtn = itemView.findViewById(R.id.buttonArrowDown);
            recyclerViewItems = itemView.findViewById(R.id.recyclerView_customerOrderHistory);




        }
    }
}
