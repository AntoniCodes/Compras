package com.example.compras.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.compras.MainActivity;
import com.example.compras.Productos;
import com.example.compras.R;

import java.util.ArrayList;

public class productosAdapter extends RecyclerView.Adapter<productosAdapter.TodoVH> {

    private Context context;

    private ArrayList<Productos> objetos;

    private int cardLayout;

    public productosAdapter(Context context, ArrayList<Productos> objetos, int cardLayout) {
        this.context = context;
        this.objetos = objetos;
        this.cardLayout = cardLayout;
    }

    @NonNull
    @Override
    public TodoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoView = LayoutInflater.from(context).inflate(cardLayout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toDoView.setLayoutParams(layoutParams);
        return new TodoVH(toDoView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoVH holder, int position) {
        Productos productos = objetos.get(position);
        holder.lblNombreProducto.setText(productos.getNombre());
        holder.lblCantidadProducto.setText(String.valueOf(productos.getCantidad()));
        holder.lblPrecioProducto.setText(String.valueOf(productos.getPrecio()));

        holder.btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmaEliminacion("Estas seguro de eliminar "+productos.getNombre().toString(),productos).show();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return objetos.size();
    }

    public class TodoVH extends RecyclerView.ViewHolder{

        TextView lblNombreProducto, lblCantidadProducto, lblPrecioProducto;
        ImageButton btnEliminarProducto;
        public TodoVH(@NonNull View itemView) {
            super(itemView);
            lblNombreProducto = itemView.findViewById(R.id.lblNombreProducto);
            lblCantidadProducto = itemView.findViewById(R.id.lblCantidadProducto);
            lblPrecioProducto = itemView.findViewById(R.id.lblPrecioProducto);
            btnEliminarProducto = itemView.findViewById(R.id.btnEliminarProducto);

        }
    }

    public AlertDialog confirmaEliminacion(String titulo, Productos producto){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setCancelable(false);
        builder.setNegativeButton("no",null);
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objetos.remove(producto);
                notifyDataSetChanged();
            }
        });
        return builder.create();
    }



}
