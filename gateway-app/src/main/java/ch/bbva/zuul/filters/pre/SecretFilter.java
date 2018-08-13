package ch.ggf.zuul.filters.pre;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SecretFilter extends ZuulFilter {

	@Autowired
	protected OAuth2RestOperations restTemplate;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();

		ClientCredentialsResourceDetails details = (ClientCredentialsResourceDetails)restTemplate.getResource();
		details.setClientSecret(req.getHeader(HeaderNames.FACADE_KEY));
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 3;
	}

}
