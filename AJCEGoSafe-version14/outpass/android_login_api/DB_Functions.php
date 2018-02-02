<?php
 
/**
 * @author Ravi Tamada
 * @link https://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/ Complete tutorial
 */
 
class DB_Functions {
 
    private $conn;
 
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    
    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($admno, $password) {
 
        $stmt = $this->conn->prepare("SELECT * FROM table_login WHERE admission_no = ?");
 
        $stmt->bind_param("s", $admno);
 
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            // verifying user password
            $salt = $user['password'];
           /*  $encrypted_password = $user['encrypted_password'];
            $hash = $this->checkhashSSHA($salt, $password); */
            // check for password equality
            if ($salt == $password) {
                // user authentication details are correct
                return $user;
            }
        } else {
            return NULL;
        }
    }
	
	/**
     * Get student information
     */
    public function getStudentInformation($admno) {
 
        $stmt = $this->conn->prepare("SELECT * FROM table_studentinfo WHERE admission_no = ?");
 
        $stmt->bind_param("s", $admno);
 
        if ($stmt->execute()) {
            $student = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $student;
           
        } 
		else {
            return NULL;
        }
    }
 
    /**
     * Check user is existed or not
     */
    public function isUserExisted($admno) {
        $stmt = $this->conn->prepare("SELECT admission_no from table_login WHERE admission_no = ?");
 
        $stmt->bind_param("s", $admno);
 
        $stmt->execute();
 
        $stmt->store_result();
 
        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }
 
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
 
    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
    public function checkhashSSHA($salt, $password) {
 
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
 
        return $hash;
    }
 
}
 
?>