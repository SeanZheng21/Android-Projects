package com.example.vocabulary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_item_detail.*

import kotlinx.android.synthetic.main.activity_item_list.fab
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.item_detail_container

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private var quizMode: Boolean = false
    private var myAdapter: SimpleItemRecyclerViewAdapter = SimpleItemRecyclerViewAdapter(this, VocabularyContent.ITEMS, twoPane, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(item_list)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId){
            R.id.menu_vocab -> {
                Log.d("menu","Vocabulaire!!!")
                myAdapter.quizMode = false
                return true
            } R.id.menu_quiz -> {
            Log.d("menu","Quizz!!!")
            myAdapter.quizMode = true
            return true
        } else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        myAdapter = SimpleItemRecyclerViewAdapter(this, VocabularyContent.ITEMS, twoPane, false)
        recyclerView.adapter = myAdapter
    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: List<VocabularyContent.WordItem>,
        private val twoPane: Boolean,
        var quizMode: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as VocabularyContent.WordItem
                if (!quizMode) {
                    if (twoPane) {
                        val fragment = ItemDetailFragment().apply {
                            arguments = Bundle().apply {
                                putString(ItemDetailFragment.ARG_ITEM_ID, item.id)
                            }
                        }
                        parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                    } else {
                        val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                            putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
                        }
                        v.context.startActivity(intent)
                    }
                } else {
                if (twoPane) {
                    val fragment = ItemQuizFragment().apply {
                        arguments = Bundle().apply {
                            putString(ItemQuizFragment.ARG_ITEM_ID, item.id)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_quiz_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemQuizActivity::class.java).apply {
                        putExtra(ItemQuizFragment.ARG_ITEM_ID, item.id)
                    }
                    v.context.startActivity(intent)
                }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id
            holder.contentView.text = item.content

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}
