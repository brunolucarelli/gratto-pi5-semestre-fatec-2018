<?php

include_once './Entidade/Usuario.php';
include_once 'DBConnection.php';
include_once 'IUsuario.php';

//Classe final para impedir que a mesma seja herdada
final class UsuarioDAO implements IUsuario {

    protected $cmdSelects = "";

    /* Método construtor publico, classe permite novas intancias */

    public function __construct() {
        
    }

    public function insert(\Usuario $usuario) {
        $this->con = DBConnection::gerarInstancia();
        if ($this->con) {
            
            $cmd = "INSERT INTO usuario (Nome, Email, Senha, Foto, Data_Nascimento, Sexo, Telefone, Cidade, Estado, Ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            $stm = $this->con->prepare($cmd);
 
            $stm->bindValue(1, $nome->getNome());
            $stm->bindValue(2, $email->getEmail());
            $stm->bindValue(3, $senha->getSenha());
            $stm->bindValue(4, $foto->getFoto());
            $stm->bindValue(5, $data_nascimento->getData_nascimento());
            $stm->bindValue(6, $sexo->getSexo());
            $stm->bindValue(7, $tel->getTel());
            $stm->bindValue(8, $cidade->getCidade());
            $stm->bindValue(9, $estado->getEstado());
            $stm->bindValue(10, $ativo->getAtivo());

            try {
                $stm->execute();
                if ($stm->rowCount() > 0) {
                    return SUCESSO;
                } else {
                    return SEM_REGISTROS;
                }
            } catch (PDOException $e) {
                return ERRO_INSTRUCAO;
            }
        } else {
            return ERRO_DB;
        }
    }

    public function select($id) {
        $this->con = DBConnection::gerarInstancia();
        if ($this->con) {
            $cmd = "SELECT * FROM usuario WHERE id = (?)";
            $stm = $this->con->prepare($cmd);
            $stm->bindValue(1, $usuario->$id);
            try {
                $stm->execute();
                if ($stm->rowCount() > 0) {
                    return $stm->fetch(PDO::FETCH_ASSOC);
                } else {
                    return SEM_REGISTROS;
                }
            } catch (PDOException $e) {
                return ERRO_INSTRUCAO;
            }
        } else {
            return ERRO_DB;
        }
    }
    
    
    public function login(Usuario $u) {
        $this->con = DBConnection::gerarInstancia();
        if ($this->con) {
            $cmd = "SELECT * FROM usuario WHERE email = (?) AND senha = (?)";
            $stm = $this->con->prepare($cmd);
            $stm->bindValue(1, $u->getEmail());
            $stm->bindValue(2, $u->getSenha());
            try {
                $stm->execute();
                if ($stm->rowCount() > 0) {
                    return $stm->fetch(PDO::FETCH_ASSOC);
                } else {
                    return SEM_REGISTROS;
                }
            } catch (PDOException $e) {
                return ERRO_INSTRUCAO;
            }
        } else {
            return ERRO_DB;
        }
    }
    
    

}
