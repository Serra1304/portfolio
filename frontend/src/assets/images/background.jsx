import * as React from "react"
const SvgComponent = (props) => (
  <svg xmlns="http://www.w3.org/2000/svg" width={900} height={700} {...props}>
    <defs>
      <radialGradient id="c" cx="50%" cy="50%" r={1.5} fx="50%" fy="50%">
        <stop
          offset={0}
          style={{
            stopColor: "#fff",
            stopOpacity: 1,
          }}
        />
        <stop
          offset={0.01}
          style={{
            stopColor: "#4ec3e0",
            stopOpacity: 1,
          }}
        />
        <stop
          offset={0.05}
          style={{
            stopColor: "#4ec3e0",
            stopOpacity: 0,
          }}
        />
      </radialGradient>
      <radialGradient id="a" cx="50%" cy="50%" r={2.5} fx="50%" fy="50%">
        <stop
          offset={0}
          style={{
            stopColor: "#fff",
            stopOpacity: 1,
          }}
        />
        <stop
          offset={0.01}
          style={{
            stopColor: "#4ec3e0",
            stopOpacity: 1,
          }}
        />
        <stop
          offset={0.05}
          style={{
            stopColor: "#4ec3e0",
            stopOpacity: 0,
          }}
        />
      </radialGradient>
      <radialGradient id="b" cx="50%" cy="50%" r={3.5} fx="50%" fy="50%">
        <stop
          offset={0}
          style={{
            stopColor: "#fff",
            stopOpacity: 1,
          }}
        />
        <stop
          offset={0.01}
          style={{
            stopColor: "#4ec3e0",
            stopOpacity: 1,
          }}
        />
        <stop
          offset={0.05}
          style={{
            stopColor: "#4ec3e0",
            stopOpacity: 0,
          }}
        />
      </radialGradient>
      <linearGradient id="d" x1="0%" x2="100%" y1="0%" y2="0%">
        <stop
          offset={0}
          style={{
            stopOpacity: 1,
            stopColor: "#fff",
          }}
        />
        <stop
          offset={0.05}
          style={{
            stopOpacity: 1,
            stopColor: "#00fffd",
          }}
        />
        <stop
          offset={0.232}
          style={{
            stopOpacity: 1,
            stopColor: "#1e94ae",
          }}
        />
        <stop
          offset={0.701}
          style={{
            stopOpacity: 1,
            stopColor: "#1e94ae",
          }}
        />
        <stop
          offset={0.903}
          style={{
            stopOpacity: 1,
            stopColor: "#00fffd",
          }}
        />
        <stop
          offset={1}
          style={{
            stopOpacity: 1,
            stopColor: "#fff",
          }}
        />
      </linearGradient>
      <linearGradient id="f" x1="0%" x2="100%" y1="0%" y2="0%">
        <stop
          offset={0}
          style={{
            stopOpacity: 1,
            stopColor: "#fff",
          }}
        />
        <stop
          offset={0}
          style={{
            stopOpacity: 1,
            stopColor: "#00fffd",
          }}
        />
        <stop
          offset={0.232}
          style={{
            stopOpacity: 1,
            stopColor: "#1e94ae",
          }}
        />
        <stop
          offset={1}
          style={{
            stopOpacity: 1,
            stopColor: "#1e94ae",
          }}
        />
      </linearGradient>
      <linearGradient id="e" x1="0%" x2="100%" y1="0%" y2="0%">
        <stop
          offset={0}
          style={{
            stopOpacity: 1,
            stopColor: "#1e94ae",
          }}
        />
        <stop
          offset={0.701}
          style={{
            stopOpacity: 1,
            stopColor: "#1e94ae",
          }}
        />
        <stop
          offset={0.903}
          style={{
            stopOpacity: 1,
            stopColor: "#00fffd",
          }}
        />
        <stop
          offset={1}
          style={{
            stopOpacity: 1,
            stopColor: "#fff",
          }}
        />
      </linearGradient>
    </defs>
    <rect
      width="100%"
      height="100%"
      style={{
        fill: "#282c34",
        fillRule: "evenodd",
        strokeWidth: 0.256345,
        strokeLinecap: "round",
      }}
    />
    <circle cx="4%" cy="93%" r={40} fill="url(#a)" />
    <circle cx="8%" cy="79%" r={40} fill="url(#b)" />
    <circle cx="17%" cy="89%" r={40} fill="url(#c)" />
    <circle cx="27%" cy="97%" r={40} fill="url(#c)" />
    <circle cx="29%" cy="84%" r={40} fill="url(#a)" />
    <circle cx="42%" cy="94%" r={40} fill="url(#b)" />
    <circle cx="54%" cy="84%" r={40} fill="url(#c)" />
    <circle cx="69%" cy="69%" r={40} fill="url(#c)" />
    <circle cx="71%" cy="95%" r={40} fill="url(#c)" />
    <circle cx="81%" cy="78%" r={40} fill="url(#b)" />
    <circle cx="86%" cy="88%" r={40} fill="url(#c)" />
    <circle cx="89%" cy="43%" r={40} fill="url(#c)" />
    <circle cx="92%" cy="59%" r={40} fill="url(#a)" />
    <circle cx="97%" cy="75%" r={40} fill="url(#a)" />
    <circle cx="97%" cy="87%" r={40} fill="url(#c)" />
    <g strokeWidth={1.5}>
      <line x1="4%" x2="8%" y1="93%" y2="79%" stroke="url(#d)" />
      <line x1="4%" x2="17%" y1="93%" y2="89%" stroke="url(#d)" />
      <line x1="8%" x2="17%" y1="79%" y2="89%" stroke="url(#d)" />
      <line x1="8%" x2="29%" y1="79%" y2="84%" stroke="url(#d)" />
      <line x1="17%" x2="29%" y1="89%" y2="84%" stroke="url(#d)" />
      <line x1="17%" x2="27%" y1="89%" y2="97%" stroke="url(#d)" />
      <line x1="27%" x2="42%" y1="97%" y2="94%" stroke="url(#d)" />
      <line x1="29%" x2="42%" y1="84%" y2="94%" stroke="url(#d)" />
      <line x1="42%" x2="54%" y1="94%" y2="84%" stroke="url(#d)" />
      <line x1="54%" x2="69%" y1="84%" y2="69%" stroke="url(#d)" />
      <line x1="54%" x2="71%" y1="84%" y2="95%" stroke="url(#d)" />
      <line x1="54%" x2="81%" y1="84%" y2="78%" stroke="url(#d)" />
      <line x1="69%" x2="92%" y1="69%" y2="59%" stroke="url(#d)" />
      <line x1="69%" x2="97%" y1="69%" y2="75%" stroke="url(#d)" />
      <line x1="69%" x2="81%" y1="69%" y2="78%" stroke="url(#d)" />
      <line x1="71%" x2="81%" y1="95%" y2="78%" stroke="url(#d)" />
      <line x1="71%" x2="86%" y1="95%" y2="88%" stroke="url(#d)" />
      <line x1="81%" x2="92%" y1="78%" y2="59%" stroke="url(#d)" />
      <line x1="81%" x2="86%" y1="78%" y2="88%" stroke="url(#d)" />
      <line x1="86%" x2="97%" y1="88%" y2="75%" stroke="url(#d)" />
      <line x1="86%" x2="97%" y1="88%" y2="87%" stroke="url(#d)" />
      <line x1="89%" x2="92%" y1="43%" y2="59%" stroke="url(#d)" />
      <line x1="92%" x2="97%" y1="59%" y2="87%" stroke="url(#d)" />
      <line x1="0%" x2="8%" y1="83%" y2="79%" stroke="url(#e)" />
      <line x1="0%" x2="4%" y1="90%" y2="93%" stroke="url(#e)" />
      <line x1="8%" x2="4%" y1="100%" y2="93%" stroke="url(#f)" />
      <line x1="15%" x2="29%" y1="100%" y2="84%" stroke="url(#e)" />
      <line x1="21%" x2="27%" y1="100%" y2="97%" stroke="url(#e)" />
      <line x1="33%" x2="29%" y1="100%" y2="84%" stroke="url(#f)" />
      <line x1="38%" x2="42%" y1="100%" y2="94%" stroke="url(#e)" />
      <line x1="45%" x2="42%" y1="100%" y2="94%" stroke="url(#f)" />
      <line x1="49%" x2="54%" y1="100%" y2="84%" stroke="url(#e)" />
      <line x1="65%" x2="71%" y1="100%" y2="95%" stroke="url(#e)" />
      <line x1="75%" x2="71%" y1="100%" y2="95%" stroke="url(#f)" />
      <line x1="80%" x2="86%" y1="100%" y2="88%" stroke="url(#e)" />
      <line x1="93%" x2="97%" y1="100%" y2="87%" stroke="url(#e)" />
      <line x1="100%" x2="86%" y1="98%" y2="88%" stroke="url(#f)" />
      <line x1="100%" x2="97%" y1="90%" y2="87%" stroke="url(#f)" />
      <line x1="100%" x2="97%" y1="72%" y2="75%" stroke="url(#f)" />
      <line x1="100%" x2="92%" y1="65%" y2="59%" stroke="url(#f)" />
      <line x1="100%" x2="92%" y1="53%" y2="59%" stroke="url(#f)" />
      <line x1="100%" x2="89%" y1="48%" y2="43%" stroke="url(#f)" />
      <line x1="100%" x2="89%" y1="38%" y2="43%" stroke="url(#f)" />
      <line x1="100%" x2="89%" y1="35%" y2="43%" stroke="url(#f)" />
    </g>
  </svg>
)
export default SvgComponent
