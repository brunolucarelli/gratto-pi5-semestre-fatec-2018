<?php

include_once './BLL/UsuarioBLL.php';
include_once './Entidade/Usuario.php';
include_once './Funcoes/gerarJSON.php';
include_once './DAO/Config.php';

if(isset($_POST['senha']) && isset($_POST['email'])){
    $usuario = new Usuario();
    $usuario->setEmail($_POST['email']);
    $usuario->setSenha($_POST['senha']);
    $usuarioBLL = new UsuarioBLL();
    echo $usuarioBLL->logarUsuario($usuario);
}
else{
    echo gerarArrayJson(PARAMETROS_INVALIDOS, "Parâmetros passados invalidos!", null);
}