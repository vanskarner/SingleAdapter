# SingleAdapter
Use a single adapter to handle views with RecyclerView. Library built around Recyclerview.Adapter

## Basic quick start:
### 1. Implement BindItem in your model class
```java
public class WomanModel implements BindItem{
    ...
}
```
### 2. Initialize SingleAdapter
```java
SingleAdapter singleAdapter = new SingleAdapter();
```
### 3. Create a class that implements BindAdapter
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
### 4. Add the BindAdapter implementation, then set the data and finally set singleadapter in the recyclerview
```java
 singleAdapter.add(new LinearAdapter());
 singleAdapter.set(DataProvider.sampleData());
 recyclerView.setAdapter(singleAdapter);
 ```

## More quick guides
https://github.com/vanskarner/SingleAdapter/wiki

| Simple | Listeners | DiffUtil | Progress |
| ------------- | ------------- | ------------- | ------------- |
| ![Alt Text](https://github.com/vanskarner/SingleAdapter/blob/refactoring/info/simple_example.gif)  | ![Alt Text](https://github.com/vanskarner/SingleAdapter/blob/refactoring/info/listener_example.gif)  | ![Alt Text](https://github.com/vanskarner/SingleAdapter/blob/refactoring/info/diff_example.gif)  | ![Alt Text](https://github.com/vanskarner/SingleAdapter/blob/refactoring/info/progress_example.gif)  |

---
Developed by the team [Vanskarner](https://github.com/vanskarner)  :grin:
