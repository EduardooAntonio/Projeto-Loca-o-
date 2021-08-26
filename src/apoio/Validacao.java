/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import dao.UsuarioDAO;
import entidade.Usuario;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Fabricio Pretto
 */
public class Validacao {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean validarDataDMA (int d, int m, int a) {
        boolean correto = true;
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            // valida o dia
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
        return (correto);
    }

    public static boolean validarDataFormatada (String dataComFormato) {
        String[] data = dataComFormato.split("/");
        return (validarDataDMA(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

    public static void validarTelefone(JFormattedTextField campo) {
        if (campo.getText().trim().length() < 14) {
            Formatacao.formatarTelefone(campo);
        }
    }
    
    public static boolean validaUsuario(Usuario user) {
        boolean r = true;
        try {
            UsuarioDAO u = new UsuarioDAO();
            Usuario usuario = u.consultarUsuario(user.getUsuario());
            if (user.getUsuario().trim().equals(usuario.getUsuario().trim())) {
                r = false;
            }
        } catch (NullPointerException e) {
            r = true;
        }
        return r;
    }
    
    public static boolean validaSenha(Usuario user) {
        boolean r = false;
        try {
            UsuarioDAO u = new UsuarioDAO();
            Usuario usuario = u.consultarUsuario(user.getUsuario());
            if (user.getSenha().trim().equals(usuario.getSenha().trim())) {
                r = true;
            }
        } catch (NullPointerException e) {
            r = false;
        }
        return r;
    }

    public static boolean validaNumero(String campo) {
        boolean r = false;
        try {
            return campo.matches("[0-9]+");
        } catch (NullPointerException e) {
            r = true;
        }
        return r;
    }
}
