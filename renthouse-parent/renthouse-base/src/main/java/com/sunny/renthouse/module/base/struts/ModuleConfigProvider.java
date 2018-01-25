package com.sunny.renthouse.module.base.struts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.config.StrutsXmlConfigurationProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.opensymphony.xwork2.FileManagerFactory;
import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.ConfigurationProvider;
import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.location.LocatableProperties;
import com.sunny.renthouse.module.filter.FrameworkFilter;

public class ModuleConfigProvider implements ConfigurationProvider {
	private static final Log LOGGER = LogFactory
			.getLog(ModuleConfigProvider.class);

	private PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	private List<ConfigurationProvider> providers = new ArrayList<ConfigurationProvider>();
	
	public ModuleConfigProvider() {
		try {
			ServletContext servletContext = FrameworkFilter.getServletContext();
			Resource[] resources = resolver
					.getResources("classpath*:com/sunny/renthouse/**/server/META-INF/struts.xml");
			for (Resource resource : resources) {
				String path = resource.getURL().getPath();
				String classpath = path.substring(path
						.lastIndexOf("com/sunny/renthouse/"));
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("[Framework] add action config: " + classpath);
				}
				providers.add(new StrutsXmlConfigurationProvider(classpath,
						true, servletContext));
			}
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
	
	@Override
	public void destroy() {
		for (ConfigurationProvider provider : providers) {
			provider.destroy();
		}
	}

	@Override
	public void init(Configuration config) throws ConfigurationException {
		for (ConfigurationProvider provider : providers) {
			provider.init(config);
		}
	}

	@Override
	public boolean needsReload() {
		for (ConfigurationProvider provider : providers) {
			if (provider.needsReload()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void register(ContainerBuilder builder, LocatableProperties properties)
			throws ConfigurationException {
		for (ConfigurationProvider provider : providers) {
			provider.register(builder, properties);
		}
	}

	@Override
	public void loadPackages() throws ConfigurationException {
		for (ConfigurationProvider provider : providers) {
			provider.loadPackages();
		}
		
	}
	@Inject
	public void setObjectFactory(ObjectFactory objectFactory) {
		for (ConfigurationProvider provider : providers) {
			StrutsXmlConfigurationProvider p = (StrutsXmlConfigurationProvider)provider;
			p.setObjectFactory(objectFactory);
		}
	}
	
	@Inject
    public void setFileManagerFactory(FileManagerFactory fileManagerFactory) {
		for (ConfigurationProvider provider : providers) {
			StrutsXmlConfigurationProvider p = (StrutsXmlConfigurationProvider)provider;
			p.setFileManagerFactory(fileManagerFactory);
		}
    }
}
