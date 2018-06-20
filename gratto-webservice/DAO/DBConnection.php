<?php


include_once 'Config.php';

/** Essa classe sera responsavel pela conexão com a base de dados Sera utilizado a classe PDO, cujo qual sera responsavel pela conexão 
 *  **/
 final class DBConnection {
     
    /**** Declarando os atributos da classe ****/

    //Variaveis de classe 
    private static $pdo;
    
    //Método construtor da classe definido como privado para impedir essa classe seja instanciada
    private function __construct() {
        
    }

     /* 
     * @return false ou objeto PDO
     */
    //Método de classe que cria a conexão com o banco de dados (retornando um objeto pdo ou false para erros)
    private static function criarConexao() {
    
        try {
            $config = array(PDO::ATTR_EMULATE_PREPARES => FALSE, // força o pdo a emular o prepared statement caso o driver não suporte
                            PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES UTF8", // retorna todo result set como uft8
                            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION, // retorna PDOExceptions como erros
                            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_BOTH); // retorna result set com vetor indexado
    
            self::$pdo = new PDO("mysql:dbname=" . DBNAME . ";host=". HOST, USER, PASSWORD, $config);

            return self::$pdo;
    
        } catch(PDOException $e){
            die("Error : " . $e->getMessage());
        }
    }
     
    /* 
     * @return método criar conexão
     */
    //Método de classe que retornara uma instância nova ou já existente do PDO (retorna objeto pdo ou false)
    public static function gerarInstancia() {
        //Se o objeto já tiver sido instânciado anteriormente e a variavel static conter sua referencia, retorna sua referencia
        if (isset( self::$pdo)) {
            return self::$pdo; //retornando instancia já criada
        }
        //Instancia um novo objeto pdo e o retorna
        else {            
            return self::criarConexao(); //retornando umva nova instancia ou false (erro)
        }
    }

}
