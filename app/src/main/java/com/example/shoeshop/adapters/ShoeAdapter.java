package com.example.shoeshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.example.shoeshop.R;
import com.example.shoeshop.activities.DetailActivity;
import com.example.shoeshop.daos.CartDAO;
import com.example.shoeshop.model.CartItemDTO;
import com.example.shoeshop.model.ShoesDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoeAdapter extends BaseAdapter {
    private ArrayList<ShoesDTO> shoesDTOList;

    public void setFruitDTOList(ArrayList<ShoesDTO> shoesDTOList) {
        this.shoesDTOList = shoesDTOList;
    }

    @Override
    public int getCount() {
        return shoesDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoesDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return shoesDTOList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.shoe, parent, false);
        }

        ShoesDTO dto = (ShoesDTO) getItem(position);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        ImageView imgView = convertView.findViewById(R.id.imgFruitView);
        Button btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
        Button btnDetail = convertView.findViewById(R.id.btnDetail);
        txtName.setText(dto.getName());
        txtPrice.setText(dto.getPrice() + "$");
        try {
            Picasso.get().load(dto.getImage()).fit().into(imgView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDAO dao = new CartDAO(v.getContext());
                try {
                    CartItemDTO cartItem = dao.getCartItem(dto.getId());
                    if (cartItem == null) {
                        cartItem = new CartItemDTO(dto.getId(), dto.getName(), 1, dto.getPrice(), dto.getImage());
                        dao.addToCart(cartItem);
                    } else {
                        int quantity = cartItem.getQuantity() + 1;
                        dao.updateCart(dto.getId(), quantity);
                    }
                    Toast.makeText(v.getContext(), "Added to Cart", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("DTO",dto);
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
