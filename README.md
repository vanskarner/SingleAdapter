# SingleAdapter
Use a single adapter to handle views with RecyclerView. Library built around Recyclerview.Adapter

## Basic quick start:
### 1. Implement BindItem in your model class
```java
public class WomanModel implements BindItem{
    ...
}
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
### 3. Initialize SingleAdapter, add BindAdapter implementation, set the data and set singleadapter in the recyclerview
```java
 SingleAdapter singleAdapter = new SingleAdapter();
 singleAdapter.add(new LinearAdapter());
 singleAdapter.set(DataProvider.sampleData());
 recyclerView.setAdapter(singleAdapter);
 ```
 
## Communication
- For bugs: [Here](https://github.com/vanskarner/SingleAdapter/labels/bug)
- For contributions: [Here](https://github.com/vanskarner/SingleAdapter/labels/enhancement)

## More quick guides
https://github.com/vanskarner/SingleAdapter/wiki

| Simple | Listeners | DiffUtil | Progress |
| ------------- | ------------- | ------------- | ------------- |
| ![simple_example](https://user-images.githubusercontent.com/39975255/179115142-213ab9e3-79f9-4d2c-970c-01310520d81b.gif)  | ![listener_example](https://user-images.githubusercontent.com/39975255/179115612-92a1296e-9e74-4d57-affb-1c9abdfc5083.gif)  | ![diff_example](https://user-images.githubusercontent.com/39975255/179115701-acb54e9f-a2c8-4f81-aa20-8d76f106bb4c.gif)  | ![progress_example](https://user-images.githubusercontent.com/39975255/179115772-dc730f1c-b6f7-4fea-a698-76ebddd0afd9.gif)  |

---
Developed by the team [Vanskarner](https://github.com/vanskarner)  :grin:
