package model;

public enum Categoria {
    ENTRADA("Entrada"),
    PRINCIPAL("Principal"),
    SOBREMESA("Sobremesa"),
    BEBIDA("Bebida");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Categoria fromString(String texto) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.descricao.equalsIgnoreCase(texto)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada: " + texto);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
