/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_group3.LaserTag.database;

import java.sql.Connection;

/**
 *
 * @author Mette
 */
public interface DbConnection {
    public Connection createConnection();
}
