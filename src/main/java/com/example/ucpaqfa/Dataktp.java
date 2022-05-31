/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ucpaqfa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MSI NOTEBOOK
 */
@Entity
@Table(name = "dataktp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dataktp.findAll", query = "SELECT d FROM Dataktp d"),
    @NamedQuery(name = "Dataktp.findById", query = "SELECT d FROM Dataktp d WHERE d.id = :id"),
    @NamedQuery(name = "Dataktp.findByNama", query = "SELECT d FROM Dataktp d WHERE d.nama = :nama"),
    @NamedQuery(name = "Dataktp.findByTglLahir", query = "SELECT d FROM Dataktp d WHERE d.tglLahir = :tglLahir")})
public class Dataktp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "tgl_lahir")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    @Basic(optional = false)
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public Dataktp() {
    }

    public Dataktp(Integer id) {
        this.id = id;
    }

    public Dataktp(Integer id, String nama, Date tglLahir, byte[] foto) {
        this.id = id;
        this.nama = nama;
        this.tglLahir = tglLahir;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dataktp)) {
            return false;
        }
        Dataktp other = (Dataktp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ucpaqfa.Dataktp[ id=" + id + " ]";
    }
    
}
