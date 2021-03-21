package com.example.planapp.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.planapp.R
import com.example.planapp.controller.EmployeesController
import com.example.planapp.utils.CustomInputData
import com.example.planapp.utils.CustomInputDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FragmentEmployees: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_employees, container, false)
        val recyclerView: RecyclerView  = view.findViewById(R.id.lista_funcionarios);
        val fabNewEmployees: FloatingActionButton = view.findViewById(R.id.fab_new_employees)
        val controller = EmployeesController()
        recyclerView.adapter = EmployeesAdapter(controller.getEmployees())
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val listInput: MutableList<CustomInputData> = mutableListOf(CustomInputData("Informe o nome", R.drawable.ic_people),CustomInputData("Informe o telefone", R.drawable.ic_phone), CustomInputData("Informe o email", R.drawable.ic_email))
        fabNewEmployees.setOnClickListener {
            var customInputDialog = CustomInputDialog()
            customInputDialog.getDialogAttributes(view.context, inflater, R.id.input, "Cadastrar Funcionario",   listInput)
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
                    }

                }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return view
    }
}