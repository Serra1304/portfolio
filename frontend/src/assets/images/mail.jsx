import * as React from "react"
const SvgComponent = (props) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width="100%"
    height="100%"
    viewBox="0 0 256 256"
    {...props}
  >
    <g
      strokeMiterlimit={10}
      fontFamily="none"
      fontSize="none"
      fontWeight="none"
      textAnchor="none"
    >
      <path
        fill="#00000000"
        d="M0 256V0h256v256z"
        style={{
          mixBlendMode: "normal",
        }}
      />
      <path
        d="M5.5 7C3.02 7 1 9.02 1 11.5v.426L25 29l24-17.074V11.5C49 9.02 46.98 7 44.5 7zm.852 2h37.293L25 22zM1 14.027V38.5C1 40.98 3.02 43 5.5 43h39c2.48 0 4.5-2.02 4.5-4.5V14.027l-6 4.27V41H7V18.297z"
        style={{
          mixBlendMode: "normal",
        }}
        transform="scale(5.12)"
      />
    </g>
  </svg>
)
export default SvgComponent
