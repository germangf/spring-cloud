package ch.ggf.zuul.filters.pre;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.google.common.collect.Sets;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class OAuth2Filter extends ZuulFilter {

	@Value("${security.oauth2.client.clientId}")
	private String clientId;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();

    OAuth2Request oAuth2Request = new OAuth2Request(null, clientId, null, false, Sets.newHashSet("read"), null, null, null, null);

    OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, null);
    OAuth2AuthenticationDetails oAuth2AuthenticationDetails = new OAuth2AuthenticationDetails(req);
    oAuth2Authentication.setDetails(oAuth2AuthenticationDetails);

    SecurityContextHolder.getContext().setAuthentication(oAuth2Authentication);

		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 4;
	}

}
