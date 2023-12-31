import * as React from "react"

const SvgComponent = (props) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={600}
    height={600}
    fill="none"
    viewBox="0 0 12 12"
    {...props}
  >
    <path
      d="M1.638 1.473h8.587v8.751H1.638z"
      style={{
        fill: "#fff",
        fillOpacity: 1,
        fillRule: "evenodd",
        stroke: "none",
        strokeWidth: 0.0678138,
        strokeOpacity: 1,
      }}
    />
    <path
      fill="#0a66c2"
      d="M10.225 10.225H8.447V7.44c0-.664-.012-1.519-.925-1.519-.926 0-1.068.724-1.068 1.47v2.834H4.676V4.498h1.707v.783h.024c.348-.594.996-.95 1.684-.925 1.802 0 2.135 1.185 2.135 2.728l-.001 3.14ZM2.67 3.715a1.037 1.037 0 0 1-1.032-1.031c0-.566.466-1.032 1.032-1.032.566 0 1.031.466 1.032 1.032 0 .566-.466 1.032-1.032 1.032zm.889 6.51h-1.78V4.498h1.78zM11.11 0H.885A.88.88 0 0 0 0 .866v10.268A.88.88 0 0 0 .885 12h10.226a.882.882 0 0 0 .889-.866V.865a.88.88 0 0 0-.889-.864z"
    />
  </svg>
)

export default SvgComponent
