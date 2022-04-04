import async from "../components/Async";

import {
  Layout as LayoutIcon,
  Sliders as SlidersIcon,
  Users as UsersIcon
} from "react-feather";

// Landing
import Landing from "../pages/components/landing/Landing";
import Home from "../pages/components/landing/Home";
// Auth
import SignIn from "../pages/auth/SignIn";
import SignUp from "../pages/auth/SignUp";
import ResetPassword from "../pages/auth/ResetPassword";
import Page404 from "../pages/auth/Page404";
import Page500 from "../pages/auth/Page500";

// Layouts


// Misc
import Blank from "../pages/misc/Blank";

// UI Elements

// Notifications
import Notifications from "../pages/notifications/Notifications";

// Pages
import User from "../pages/pages/userManagement/User";
import Pricing from "../pages/pages/Pricing";
import NewPassword from "../pages/auth/NewPassword";

// auth
import withAuth from "../HOC/withAuth";

//component
import TourDetail from "../pages/components/TourDetail";
import Tours from "../pages/pages/tourManagement/Tours";
import {TourOrder} from "../pages/components/TourOrder";
import {TourListSideBar} from "../pages/components/TourListSideBar";

// Dashboards
const Default = async(() => import("../pages/dashboards/Default"));



// Routes
const landingRoutes = {
    path: "/",
    name: "Landing Page",
    component: Home,
    children: null
};

const tourListSideBarRoutes = {
  path: "/search-all-tour",
  name: "Tour List Sidebar",
  component: TourListSideBar,
}

const tourDetailRoutes = {
    path: "/tour-detail/:tourId",
    name: "Tour Detail",
    component: TourDetail,
}

const tourOrderRoutes = {
    path: "/checkout",
    name: "Tour Order",
    component: TourOrder,
}

const dashboardRoutes = {
  path: "/dashboard",
  name: "Dashboards",
  header: "Pages",
  badgeColor: "primary",
  icon: SlidersIcon,
  containsHome: true,
  component: withAuth(Default),
  children: null
};
// const otherRoutes = {
//   path: "/"
// }
const pageRoutes = {
  path: "/management",
  name: "Management",
  icon: LayoutIcon,
  children: [
    {
      path: "/management/tours",
      name: "Tours",
      component: withAuth(Tours)
    },
    {
      path: "/management/orders",
      name: "Orders",
      component: Pricing
    },
    {
      path: "/management/user",
      name: "User",
      component: User
    }

  ]
};

const authRoutes = {
  path: "/auth",
  name: "Auth",
  icon: UsersIcon,
  badgeColor: "secondary",
  badgeText: "Special",
  children: [
    {
      path: "/auth/sign-in",
      name: "Sign In",
      component: SignIn
    },
    {
      path: "/auth/sign-up",
      name: "Sign Up",
      component: SignUp
    },
    {
      path: "/auth/reset-password",
      name: "Reset Password",
      component: ResetPassword
    },
    {
      path: "/auth/new-password/:token",
      name: "New Password",
      component: NewPassword
    },
    {
      path: "/auth/404",
      name: "404 Page",
      component: Page404
    },
    {
      path: "/auth/500",
      name: "500 Page",
      component: Page500
    }
  ]
};

// This route is not visisble in the sidebar
const privateRoutes = {
  path: "/private",
  name: "Private",
  children: [
    {
      path: "/private/blank",
      name: "Blank Page",
      component: Blank
    }
  ]
};

// Dashboard specific routes
export const dashboard = [
  dashboardRoutes,
  pageRoutes,
  privateRoutes
];

// Landing specific routes
export const landing = [landingRoutes];
export const tourDetail = [tourDetailRoutes];
export const tourOrder = [tourOrderRoutes];
export const tourListSideBar = [tourListSideBarRoutes];
// Auth specific routes
export const page = [authRoutes];

// All routes, on display sidebar
export default [
  dashboardRoutes,
  pageRoutes,
  // authRoutes,



];
