package com.example.planapp.view
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.planapp.R
import androidx.appcompat.view.ActionMode
import com.example.planapp.controller.EmployeesController
import com.example.planapp.utils.CustomInputData
import com.example.planapp.utils.CustomInputDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FragmentEmployees: Fragment() {
    private lateinit var adapter: EmployeesAdapter
    private var toolbar: Toolbar? = null
    private var actionMode: ActionMode? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_employees, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        val recyclerView: RecyclerView  = view.findViewById(R.id.lista_funcionarios);
        val fabNewEmployees: FloatingActionButton = view.findViewById(R.id.fab_new_employees)
        val controller = EmployeesController()
        this.adapter = EmployeesAdapter(controller.getEmployees())
        recyclerView.adapter = adapter
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val listInput: MutableList<CustomInputData> = mutableListOf(CustomInputData("Informe o nome", R.drawable.ic_people), CustomInputData("Informe o telefone", R.drawable.ic_phone), CustomInputData("Informe o email", R.drawable.ic_email))
        fabNewEmployees.setOnClickListener {
            var customInputDialog = CustomInputDialog()
            customInputDialog.getDialogAttributes(view.context, inflater, R.id.input, "Cadastrar Funcionario", listInput)
        }
        val itemTouchHelperCallback =
                object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                    ): Boolean {

                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        Toast.makeText(
                                view.context,
                                "Apagado teste",
                                Toast.LENGTH_SHORT
                        ).show()
                        adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                    }
                }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        adapter.onItemClick = {
            enableActionMode(it)
        }
        adapter.onItemLongClick = {
            enableActionMode(it)
        }
        return view

    }
    private fun enableActionMode(position: Int){
        if(actionMode == null)  actionMode = (activity as MainHomeActivity?)?.startSupportActionMode(object : ActionMode.Callback{
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
               mode?.menuInflater?.inflate(R.menu.delete_menu, menu)
              return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                mode?.finish()
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                actionMode = null
            }

        })
        adapter.toggleSelection(position)
        val size = adapter.selectedItens.size()
        if(size == 0){
            actionMode?.finish()
        }else{
            actionMode?.title = "$size"
            actionMode?.invalidate()
        }
    }
}