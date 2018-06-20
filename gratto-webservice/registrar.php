<?php

include_once './BLL/UsuarioBLL.php';
include_once './Entidade/Usuario.php';
include_once './Funcoes/gerarJSON.php';
include_once './DAO/Config.php';

if(isset($_REQUEST['nome']) && isset($_REQUEST['celular']) && isset($_REQUEST['senha'])){
    $usuarioBLL = new UsuarioBLL();
    $usuario = new Usuario();
    $usuario->setNome($_REQUEST['nome']);
    $usuario->setCelular($_REQUEST['celular']);
    $usuario->setSenha($_REQUEST['senha']);    

    echo $usuarioBLL->cadastrarUsuario($usuario);
}

else{
    echo gerarArrayJson(PARAMETROS_INVALIDOS, "Parâmetros passados invalidos!", null);
}


