    package GOLD.MLL.VirtualCloset.Adapters

    import GOLD.MLL.VirtualCloset.R
    import android.content.Intent
    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    import GOLD.MLL.VirtualCloset.Cloths
    import GOLD.MLL.VirtualCloset.ProductDetails.ProductDetails
    import android.annotation.SuppressLint
    import com.bumptech.glide.load.resource.bitmap.RoundedCorners
    import com.bumptech.glide.request.RequestOptions

    @SuppressLint("NotifyDataSetChanged")
    class HomeAdapter(private var mList: List<Cloths>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
        private var fullList: List<Cloths> = mList
        private var switch: Boolean = false

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cloth_adapter, parent, false)
            val holder = ViewHolder(view)
            view.setOnClickListener{
                val intent = Intent(parent.context, ProductDetails::class.java)
                intent.putExtra("clothsItem", mList[holder.adapterPosition])
                intent.putExtra("switch", switch)
                parent.context.startActivity(intent)
            }

            return holder
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val clothsList = mList[position]
            Glide.with(holder.itemView.context)
                .asDrawable()
                .load(clothsList.photoUrl)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(holder.image)

            holder.name.text = clothsList.name
            holder.likes.text = "View User: ${clothsList.aLike}   Edit User: ${clothsList.sLike}"
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        fun switchFlip(){
            switch = !switch
        }
        fun sortByALike() {
            val sortedList = mList.sortedByDescending { it.aLike }
            mList = sortedList
            notifyDataSetChanged()
        }
        fun sortBySLike() {
            val sortedList = mList.sortedByDescending { it.sLike }
            mList = sortedList
            notifyDataSetChanged()
        }
        fun filterCloths(showShirts: Boolean, showPants: Boolean) {
            val filteredList = fullList.filter {
                (showShirts && it.typeCloth == "Shirts") || (showPants && it.typeCloth == "Pants")}
            Log.e("List", arrayListOf<Any>(showPants,showShirts,filteredList.toString()).toString())
            mList = filteredList
            notifyDataSetChanged()
        }
        fun sortRandomly() {
            mList = mList.shuffled()
            notifyDataSetChanged()
        }
        fun updateList(newList: List<Cloths>) {
            mList = newList
            fullList = newList
            notifyDataSetChanged()
        }
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = this.itemView.findViewById(R.id.image)
            val name: TextView = this.itemView.findViewById(R.id.name)
            val likes: TextView = this.itemView.findViewById(R.id.likes)
        }
    }