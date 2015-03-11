/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoint.ux.proxytest;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
import org.mitre.dsmiley.httpproxy.ProxyServlet;

/**
 *
 * @author joel_harris
 */
public class HTTPSProxyServlet extends ProxyServlet {

   private static final long serialVersionUID = -2657146208778908701L;

    private static final X509TrustManager trustManager = new X509TrustManager() {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    @Override
    protected HttpClient createHttpClient(HttpParams hcParams) {
        // Adapted from JavaSkeleton post. Fixed deprecated calls.
        // http://javaskeleton.blogspot.com/2010/07/avoiding-peer-not-authenticated-with.html
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { trustManager }, null);

            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = new ThreadSafeClientConnManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 4848, ssf));

            return new DefaultHttpClient(ccm, hcParams);

        } catch (Exception ex) {
            // fall back on base implementation
            ex.printStackTrace();
            return super.createHttpClient(hcParams);
        }
    }
}
