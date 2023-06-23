package com.example.iteonlineshop.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteonlineshop.api.models.Product;
import com.example.iteonlineshop.databinding.ViewHolderProductBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {

    public ProductAdapter() {

        super(new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProductBinding binding = ViewHolderProductBinding.inflate(layoutInflater, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product item = getItem(position);
        holder.bind(item);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ViewHolderProductBinding itemBinding;
        public ProductViewHolder(ViewHolderProductBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Product product) {
            Picasso.get().load(product.getImgUrl()).into(itemBinding.productItemImg);
            if (product.getName().length() > 30 ) {
                product.setName(product.getName().substring(0,30) + "...");
            }
            itemBinding.productItemName.setText(product.getName());
            float price = product.getPrice();
            String dollar_price = "$" + String.format("%.02f", price);
            itemBinding.productItemPrice.setText(dollar_price);
        }
    }
}
