# SingleAdapter
Use a single adapter to handle views with RecyclerView.Library built around Recyclerview.Adapter

## Quick Start: for simple views
### 1. Initialize `SingleAdapter`
```java
SingleAdapter singleAdapter = new SingleAdapter();
```
### 2. Create a class that implements BindAdapter
```java
class LinearAdapter implements BindAdapter<WomanModel, LinearAdapter.LinearVH> {

    @Override
    public LinearVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.linear_item, parent, false);
        return new LinearVH(view);
    }

    @Override
    public void onBindViewHolder(LinearVH viewHolder, WomanModel item) {
        Glide.with(viewHolder.image).load(item.getImageID()).into(viewHolder.image);
        viewHolder.name.setText(item.getFirstName());
    }

    @Override
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    static class LinearVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        LinearVH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
```
### 3. Add the above implementation to your `SingleAdapter`
```java
 singleAdapter.add(new LinearAdapter());
 ```
### 4. Set the data to `SingleAdapter`
```java
 singleAdapter.set(DataProvider.sampleData());
 ```

## More quick guides
https://github.com/vanskarner/SingleAdapter/wiki
---
Developed by the team [Vanskarner](https://github.com/vanskarner)  :grin:
