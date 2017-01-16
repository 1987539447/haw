<#--index主要页面的header标题头栏目-->
<#macro header>
<header>
    <div class='navbar'>
        <div class='navbar-inner'>
            <div class='container-fluid'>
                <a class='brand' href='${ctx}/login'>
                    <i class='icon-bar-chart'></i>
                    <span class='hidden-phone'><@spring.message "login.title"/></span>
                </a>
                <a class='toggle-nav btn pull-left' href='#'>
                    <i class='icon-circle-arrow-left icon-large'></i>
                </a>
                <ul class='nav pull-right'>
                   <#-- <li class='dropdown medium only-icon widget'>
                        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
                            <i class='icon-rss'></i>

                            <div class='label'>5</div>
                        </a>
                        <ul class='dropdown-menu'>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-user text-success'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            John Doe signed up
                                            <small class='muted'>just now</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-inbox text-error'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            New Order #002
                                            <small class='muted'>3 minutes ago</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-comment text-warning'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            America Leannon commented Flatty with veeery long text.
                                            <small class='muted'>1 hour ago</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-user text-success'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            Jane Doe signed up
                                            <small class='muted'>last week</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='divider'></li>
                            <li>
                                <a href='#'>
                                    <div class='widget-body'>
                                        <div class='pull-left icon'>
                                            <i class='icon-inbox text-error'></i>
                                        </div>
                                        <div class='pull-left text'>
                                            New Order #001
                                            <small class='muted'>1 year ago</small>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class='widget-footer'>
                                <a href='#'>All notifications</a>
                            </li>
                        </ul>
                    </li>-->
                    <li class='dropdown dark user-menu'>
                        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
                            <img alt='Mila Kunis' height='23' src='assets/images/5340.gif' width='23'/>
                            <span class='user-name hidden-phone'>${user.name!''}</span>
                            <b class='caret'></b>
                        </a>
                        <ul class='dropdown-menu'>
<#--                            <li>
                                <a href='user_profile.html'>
                                    <i class='icon-user'></i>
                                    <@spring.message "index.user.profile"/>
                                </a>
                            </li>
                            <li>
                                <a href='user_profile.html'>
                                    <i class='icon-cog'></i>
                                    <@spring.message "index.user.settings"/>
                                </a>
                            </li>
                            <li class='divider'></li>-->
                            <li>
                                <a href='logout'>
                                    <i class='icon-signout'></i>
                                    <@spring.message "index.user.signout"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
<#--                <form accept-charset="UTF-8" action="search_results.html" class="navbar-search pull-right hidden-phone"
                      method="get"/>
                <div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;"/></div>
                <button class="btn btn-link icon-search" name="button" type="submit"></button>
                <input autocomplete="off" class="search-query span2" id="q_header" name="q" placeholder="Search..."
                       type="text" value=""/>
                </form>-->
            </div>
        </div>
    </div>
</header>
</#macro>


<#--左边导航栏-->
<#macro navigation>
<div class='navigation'>
    <div class='search'>
        <form accept-charset="UTF-8" action="search_results.html" method="get"/>
        <div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;"/></div>
        <div class='search-wrapper'>
            <input autocomplete="off" class="search-query" id="q" name="q" placeholder="Search..." type="text"
                   value=""/>
            <button class="btn btn-link icon-search" name="button" type="submit"></button>
        </div>
        </form>
    </div>
    <ul id="navigationBar" class='nav nav-stacked'>
        <li class='active'>
            <a href='#'>
                <i class='icon-align-left'></i>
                <span><@spring.message "login.title"/></span>
            </a>
        </li>
    </ul>
</div>
</#macro>

<#macro mainFormHeader>
<#--<div class='page-header'>
    <h1 class='pull-left'>
        <i class='icon-dashboard'></i>
        <span id="mainFormName"><@spring.message "login.title"/></span>
    </h1>
</div>-->

</#macro>