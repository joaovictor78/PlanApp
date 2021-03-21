package com.example.corretagemapp.database

object ScriptDLL {
       val createTableCompromisso = """
                CREATE TABLE IF NOT EXISTS compromisso(
                codigo_compromisso INTEGER PRIMARY KEY AUTOINCREMENT,
                assunto TEXT NOT NULL,
                horario TEXT NOT NULL,
                descricao TEXT NOT NULL); """

       val createTableDataCompromisso = """"
            CREATE TABLE IF NOT EXISTS data_compromisso(
            id_data INTEGER PRIMARY KEY AUTOINCREMENT,
            id_compromisso INTEGER NOT NULL,
            data TEXT NOT NULL,
            FOREIGN KEY (id_compromisso) REFERENCES compromisso(codigo_compromisso) );"""
    val createFuncionario = """
            CREATE TABLE IF NOT EXISTS funcionario(
            codigo_funcionario INTEGER PRIMARY KEY AUTOINCREMENT,
            nome_funcionario TEXT NOT NULL,
            numero_funcionario TEXT NOT NULL,
            email_funcionario TEXT NOT NULL);"""
}