import { Link, useLocation } from "react-router-dom";

function Sidebar({ setIsClosed, overlayRef, wrapperRef }) {
    const location = useLocation();

    const handleLinkClick = () => {
        setIsClosed(true); // close the sidebar
        if (overlayRef.current) overlayRef.current.style.display = 'none';
        if (wrapperRef.current) wrapperRef.current.classList.remove('toggled');
    };

    return (
        <div>
            <nav className="navbar navbar-inverse fixed-top" id="sidebar-wrapper" role="navigation">
                <ul className="nav sidebar-nav">
                    <div className="sidebar-header">
                        <div className="sidebar-brand">
                            <a href="#">LMS Author</a>
                        </div>
                    </div>
                    <li><Link to="/author" onClick={handleLinkClick} className={location.pathname === "/author" ? "active" : ""}>
                    <i className="bi bi-house" style={{ marginRight: "10px" }}></i>
                    Home
                    </Link></li>
                    <li><Link to="/author/courses" onClick={handleLinkClick}>
                    <i className="bi bi-safe" style={{ marginRight: "10px" }}></i>
                    Courses
                    </Link></li>
                    <li><Link to="/author/enrollments" onClick={handleLinkClick}>Enrollments</Link></li>
                    <li><Link to="/author/profile" onClick={handleLinkClick}>Profile</Link></li>
                    <li><a href="#services">Services</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li><a href="#followme">Follow me</a></li>
                </ul>
            </nav>
        </div>
    );
}

export default Sidebar;