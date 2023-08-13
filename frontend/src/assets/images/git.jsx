import * as React from "react"

const SvgComponent = (props) => (
  
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={717.158}
    height={717.158}
    viewBox="0 0 21.515 21.515"
    className={props.className}
    {...props}
  >
    <path
      fill={props.color}
      d="M12.172 2.586a2 2 0 0 0-2.829 0l-.671.671 1.567 1.568a2.002 2.002 0 0 1 2.45 2.45l2.05 2.05a2 2 0 1 1-1.414 1.414l-1.568-1.567v4.853a2 2 0 0 1-1 3.732 2 2 0 0 1-1-3.732V8.49a2 2 0 0 1-.932-2.25L7.257 4.671 2.586 9.343a2 2 0 0 0 0 2.829l6.757 6.757a2 2 0 0 0 2.829 0l6.757-6.757a2 2 0 0 0 0-2.829zM7.929 1.172a4 4 0 0 1 5.657 0l6.757 6.757a4 4 0 0 1 0 5.657l-6.757 6.757a4 4 0 0 1-5.657 0l-6.757-6.757a4 4 0 0 1 0-5.657z"
      clipRule="evenodd"
    />
  </svg>
)

export default SvgComponent