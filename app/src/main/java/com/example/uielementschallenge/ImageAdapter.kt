package com.example.uielementschallenge

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

internal class ImageAdapter(
        private val context: Context,
        private val numbersInWords: Array<String>,
        private val numberImage: IntArray
) :
        BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    override fun getCount(): Int {
        return numbersInWords.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    @SuppressLint("InflateParams")
    override fun getView(
            position: Int,
            convertViews: View?,
            parent: ViewGroup
    ): View? {
        var convertView = convertViews
        if (layoutInflater == null) {
            layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.grid_items, null)
        }
        imageView = convertView!!.findViewById(R.id.img)
        textView = convertView.findViewById(R.id.txt)
        imageView.setImageResource(numberImage[position])
        textView.text = numbersInWords[position]
        return convertView
    }
}



/*
class ImageAdapter(private val getContext: Context, private val CustomLayoutId : Int,
                   private val CustomItem: ArrayList<ImageLayout>): ArrayAdapter<ImageLayout>
                    (getContext,CustomLayoutId, CustomItem){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        var holder: ViewHolder = ViewHolder()

        if (row == null) {

        val inflater = (getContext as Activity).layoutInflater
        row = inflater.inflate(CustomLayoutId,parent,false)

        holder.img = row.findViewById(R.id.img) as ImageView
        holder.txt = row.findViewById(R.id.txt) as TextView
            row.tag = holder

        } else {
            holder = row.tag as ViewHolder
        }

        val item = CustomItem[position]

        holder.img!!.setImageResource(item.image)
        holder.txt!!.text = item.text

        return row!!
    }

    class ViewHolder {
        internal  var img : ImageView? = null
        internal  var txt : TextView? = null
    }
}
*/

