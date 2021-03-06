// restaura el estilo guardado
applyStyleSheet();

function applyStyleSheet() {
    let tableStyle = document.getElementById("sortTable"); // TODO: investigar como reducir la redundancia de este codigo
    document.getElementById('theme').href = localStorage.getItem('csshref');
    document.getElementById('theme').className = localStorage.getItem('cssclass');
    // control del switch
    if (document.getElementById('theme').className == "night") {
        document.getElementById("flexSwitchCheckDefault").checked = true;
        if (tableStyle != null) {
            tableStyle.classList.add("table-dark");
            tableStyle.classList.remove("table-light");
        }

    }else{
        document.getElementById("flexSwitchCheckDefault").checked = false;
        if (tableStyle != null) {
            tableStyle.classList.add("table-light");
            tableStyle.classList.remove("table-dark");
        }
    }
}

window.addEventListener('DOMContentLoaded', () => {
    let scrollPos = 0;
    const mainNav = document.getElementById('mainNav');
    const headerHeight = mainNav.clientHeight;
    window.addEventListener('scroll', function() {
        const currentTop = document.body.getBoundingClientRect().top * -1;
        if ( currentTop < scrollPos) {
            // Scrolling Up
            if (currentTop > 0 && mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-visible');
            } else {
                mainNav.classList.remove('is-visible', 'is-fixed');
            }
        } else {
            // Scrolling Down
            mainNav.classList.remove(['is-visible']);
            if (currentTop > headerHeight && !mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-fixed');
            }
        }
        scrollPos = currentTop;
    });

    // controla el estilo de dia/noche
    document.getElementById('flexSwitchCheckDefault').onclick = function() {
        let tableStyle = document.getElementById("sortTable"); // para las table
        if (document.getElementById('theme').className == "night") {
          document.getElementById('theme').href = location.protocol+'//'+location.host.slice(0,location.href.lastIndexOf("/"))+"/css/day.css";
          document.getElementById('theme').className = "day";
          document.getElementById("flexSwitchCheckDefault").checked = false
          if (tableStyle != null) {
            tableStyle.classList.add("table-light");
            tableStyle.classList.remove("table-dark");
          }          
        } else {
          document.getElementById('theme').href = location.protocol+"//"+location.host.slice(0,location.href.lastIndexOf("/"))+"/css/night.css";
          document.getElementById('theme').className = "night";
          document.getElementById("flexSwitchCheckDefault").checked = true;
          if (tableStyle != null) {
            tableStyle.classList.add("table-dark");
            tableStyle.classList.remove("table-light");
          }
        }
        localStorage.setItem('csshref', document.getElementById('theme').href);
        localStorage.setItem('cssclass', document.getElementById('theme').className);
        
      };

})

/*!
* Start Bootstrap - Clean Blog v6.0.8 (https://startbootstrap.com/theme/clean-blog)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-clean-blog/blob/master/LICENSE)
*/