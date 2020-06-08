package a.m.restaurant_automation.chef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.responseModel.GetOrderResponseModel;

public class ChefDashboardAdapter extends RecyclerView.Adapter<ChefDashboardAdapter.ViewHolder> {
    ArrayList<GetOrderResponseModel> orders;
    Context context;
    public ChefDashboardAdapter(ArrayList<GetOrderResponseModel> orders){
        this.orders = orders;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_chef_screen_orders, parent, false);
        this.context = context;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tableIdTV.setText(""+orders.get(position).tableId);
        holder.orderIdTV.setText(""+orders.get(position).orderId);
        holder.personNameTV.setText(orders.get(position).firstName+" "+orders.get(position).lastName);

        ChefDashBoardItemsAdapter itemsAdapter = new ChefDashBoardItemsAdapter(orders.get(position).menuItems);
        holder.recyclerViewItems.setAdapter(itemsAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.recyclerViewItems.setLayoutManager(layoutManager);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        if(orders.get(position).orderStatusTitle.equalsIgnoreCase("Ordered")){
            holder.changeStatusButton.setText("REJECT");
        }
        else if(orders.get(position).orderStatusTitle.equalsIgnoreCase("InProgress")){
            holder.changeStatusButton.setText("DONE");
        }


        holder.changeStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.changeStatusButton.getText().toString().equalsIgnoreCase("Ordered")){

                }else{

                }
            }
        });

        holder.expandCollapseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams layoutParams = holder.recyclerViewItems.getLayoutParams();
                if(layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    holder.recyclerViewItems.setLayoutParams(layoutParams);
                    holder.expandCollapseBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
                else{
                    layoutParams.height = 90;
                    holder.recyclerViewItems.setLayoutParams(layoutParams);
                    holder.expandCollapseBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tableIdTV, orderIdTV, personNameTV;
        RecyclerView recyclerViewItems;
        Button expandCollapseBtn, changeStatusButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tableIdTV = itemView.findViewById(R.id.txtTableNumberChef);
            orderIdTV = itemView.findViewById(R.id.txtOrderNumberChef);
            personNameTV = itemView.findViewById(R.id.txtpersonNameChef);
            recyclerViewItems= itemView.findViewById(R.id.recyclerViewChefScreenOrder);
            expandCollapseBtn = itemView.findViewById(R.id.btnArrowDown);
            changeStatusButton = itemView.findViewById(R.id.buttonStatusChange);
            cardView = itemView.findViewById(R.id.cardViewOrdersChef);
        }
    }
}
