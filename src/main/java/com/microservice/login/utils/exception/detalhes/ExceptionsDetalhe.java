package com.microservice.login.utils.exception.detalhes;

public class ExceptionsDetalhe {
    private int status;
    private String detalhe;
    private long timestamp;
    private String mensagemDesenvolvedor;

    public ExceptionsDetalhe() {
    }

    public int getStatus() {
        return status;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }

    public static final class ExceptionsDetalheBuilder {

        private int status;
        private String detalhe;
        private long timestamp;
        private String mensagemDesenvolvedor;

        private ExceptionsDetalheBuilder() {
        }

        public static ExceptionsDetalheBuilder newBuilder() {
            return new ExceptionsDetalheBuilder();
        }


        public ExceptionsDetalheBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ExceptionsDetalheBuilder detalhe(String detalhe) {
            this.detalhe = detalhe;
            return this;
        }

        public ExceptionsDetalheBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionsDetalheBuilder mensagemDesenvolvedor(String mensagemDesenvolvedor) {
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
            return this;
        }

        public ExceptionsDetalhe build() {
            ExceptionsDetalhe exceptionsDetalhe = new ExceptionsDetalhe();
            exceptionsDetalhe.timestamp = this.timestamp;
            exceptionsDetalhe.detalhe = this.detalhe;
            exceptionsDetalhe.mensagemDesenvolvedor = this.mensagemDesenvolvedor;
            exceptionsDetalhe.status = this.status;
            return exceptionsDetalhe;
        }
    }
}
