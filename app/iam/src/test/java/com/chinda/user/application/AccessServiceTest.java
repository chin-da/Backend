package com.chinda.user.application;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class AccessServiceTest {
    @Test
    void Should_ProperlyCreated_When_GivenPEMStrings() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyPEM = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyRJjbGAYEU2yn1AJwXQx\n" +
                "kl+6k1UmuMo80oYU3N33zl1A1Ywuu9MET97YXTWaez/VfJgzuU/uibfopjfyFM/M\n" +
                "zau5QcGEW487RYrSxil+/ZJr1DNEqwR/6ZMn3fpYHjBPjJkVtd/yTWCgrEp+G1Pk\n" +
                "JxrwOKQb6+/CWj1g3HlM9KQhAEPDggkyfBOt8chojlhrZzvXXRfbv2qCJ4rqRkgJ\n" +
                "wAcZRt/7CtlYz5CElpNR49iu+CXtcnjTwG5ABBntK9UzsRMXKEnDbwY0R49SKG8s\n" +
                "Kxay54agT/5kQOk2blXQij2p+PectMcRygcbWIz6c+ucE9Ff+hjY7NH/FaQD2aRw\n" +
                "ywIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        String privateKeyPEM = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJEmNsYBgRTbKf\n" +
                "UAnBdDGSX7qTVSa4yjzShhTc3ffOXUDVjC670wRP3thdNZp7P9V8mDO5T+6Jt+im\n" +
                "N/IUz8zNq7lBwYRbjztFitLGKX79kmvUM0SrBH/pkyfd+lgeME+MmRW13/JNYKCs\n" +
                "Sn4bU+QnGvA4pBvr78JaPWDceUz0pCEAQ8OCCTJ8E63xyGiOWGtnO9ddF9u/aoIn\n" +
                "iupGSAnABxlG3/sK2VjPkISWk1Hj2K74Je1yeNPAbkAEGe0r1TOxExcoScNvBjRH\n" +
                "j1IobywrFrLnhqBP/mRA6TZuVdCKPan495y0xxHKBxtYjPpz65wT0V/6GNjs0f8V\n" +
                "pAPZpHDLAgMBAAECggEAATFEua3QNroOkcyjDNdS5pqpZppIrSvgicxrGew5vgA7\n" +
                "lDNNS6aIDgPfpfMyxAGwPcRBT5teah6sbd/tuvCIjGe0+jja6hgYB+TiNHP74XvE\n" +
                "JpwEj5G/7ciw9ythk7xBnISD5nI6OPnrugSEaZu+bKTyQRkSuVF7W5b67hPMwF6o\n" +
                "xo66S+g4AmMRg9mYz7FmOagSgmoxdmIe+QZYIPtG1ZnRexEjclbvxco4VxhBa7kC\n" +
                "m+bfF/SU97myESvv8+AfQIrj7J5+Uy0aAf+S/5UelmPNtAR3qBNgR+7njumkP/gX\n" +
                "ITcmjRlGpD+0AGSUpC4aj+ZqrZGd4q9iMrUr7tYWAQKBgQDLx49RnRJBfi6ukZ1K\n" +
                "mOfvFcOuLPQ/4Gtx03Db3/lEzDEM2bEK7lTR1vVvgbT1Wo4uCWC2MuBToxjLdPPF\n" +
                "ww2O30tFdG4pL55FwaGX8V6z+evLo1Ufb4+XwBIBsaEtdvnAfp31nmMQzUWfCHP3\n" +
                "coY4rjJBhlaWGoiWwaZv+4WlgQKBgQD8mTJYcnUFOxlQ4kpMF/1+QeeVxBhF3YOo\n" +
                "0pNeUoASGLr9WlU9vPqSyaBEFAl+XKhBGbTAlW9+mElMB3Y1lYUI7/w0Yslqmj1v\n" +
                "xlBPMGZsOF/5Y0H3FAGqRMe7qelxRfpL2zVcRl4HAjy1xdWWC3GiL9hC6F4QkHZ+\n" +
                "OS5V8r70SwKBgFQMuHFKHtiCihCYoM6+6JL1YCN8CTY7gzWMp3UZGUAmFGkfdOV0\n" +
                "TenLBqp6TkAQnKoiVhBwRZRZpgVs9IryG6p9MRexoSo9qMNOzS7VcGE9SONSN+7i\n" +
                "XdCSeRrXwAn3k6Ww08gWDvhEIl4o52P9iZzhRHtARIBe6017DAIONR6BAoGAYDnk\n" +
                "CfmgRpva6wspjo9dDCoDFPSc4pCeRUPiy/3VuA8fvMAa1OgW9LBGk2imrTKTDinC\n" +
                "x9KlpK5SzdnR2FvbUCpMA5DWdfvIB7qegWxIuFVJinGbgYpnj/ufswfpXDNyRE9/\n" +
                "pmVQEI9znTN+SCiX41cipWpr2xeNbExQMIHG6L0CgYEAywPhkENeu/Lv+FflhDN1\n" +
                "ofTPB4vG/O6xZF87Or1npUH9Lt/t5bTSVFwxo9ZIC30b3JxM5mBxr+Bm+VXZNEqi\n" +
                "IgOgl4iAK63pQBzK6+TFHYQk0WlK0jpiX1Dww3M0uCokKC4nGrFkHafRMTTZBLyg\n" +
                "c3qEiy8abVkOtqLL0s6D5NE=\n" +
                "-----END PRIVATE KEY-----";

        new AccessService(null, null, publicKeyPEM, privateKeyPEM);
    }

}